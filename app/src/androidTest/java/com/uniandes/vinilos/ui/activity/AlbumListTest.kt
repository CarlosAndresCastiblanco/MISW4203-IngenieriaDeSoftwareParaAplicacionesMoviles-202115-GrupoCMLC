package com.uniandes.vinilos.ui.activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uniandes.vinilos.Destinations
import com.uniandes.vinilos.DetailsScreen

import com.uniandes.vinilos.ListScreen
import com.uniandes.vinilos.MainActivity
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AlbumListTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            AlbumsAppTheme() {
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable(route = "home") {
                        ListScreen(navController = navController)
                    }
                    composable(route = "DETAILS_SCREEN/1") {
                        DetailsScreen("1", navController = navController)
                    }
                    composable(route="CREATE_ALBUM_SCREEN"){
                        CreateAlbumScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun validateListAlbumActivity() {
        Thread.sleep(500)
        composeRule.onNodeWithText("Lista de Álbumes").assertExists()
        Thread.sleep(500)
        composeRule.onNodeWithText("Album 1").assertExists()
        composeRule.onNodeWithText("Artista 1").assertExists()
        Thread.sleep(500)
        composeRule.onNodeWithText("Album 2").assertExists()
        composeRule.onNodeWithText("Artista 2").assertExists()
    }

    @Test
    fun validateDetailAlbumActivity() {
        Thread.sleep(500)
        composeRule.onNodeWithText("Album 1").assertExists()
        //On click details
        composeRule.onNodeWithText("Album 1").performClick()
        Thread.sleep(2000)
        //Artista
        composeRule.onNodeWithText("Artista 1").assertExists()
        //Salsa
        composeRule.onNodeWithText("Salsa").assertExists()
        //Record label
        composeRule.onNodeWithText("EMI").assertExists()
        //Validate return button OnClick
        composeRule.onNodeWithContentDescription("Back").performClick()
        // Validate Album List
        Thread.sleep(2000)
        composeRule.onNodeWithText("Album 1").assertExists()
        composeRule.onNodeWithText("Album 2").assertExists()
        Thread.sleep(1000)
    }

    @Test
    fun validateCreateAlbum(){
        Thread.sleep(500)
        composeRule.onNodeWithContentDescription("add_button").performClick()
        composeRule.onNodeWithTag("NAME").performTextInput("Cristian")
        composeRule.onNodeWithTag("COVER").performTextInput("https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg")
        composeRule.onNodeWithTag("GENRE").assertExists()
        composeRule.onNodeWithTag("DESCRIPTION").performTextInput("First description")
        composeRule.onNodeWithTag("RECORD").assertExists()
        composeRule.onNodeWithTag("DATE").assertExists()
    }
}