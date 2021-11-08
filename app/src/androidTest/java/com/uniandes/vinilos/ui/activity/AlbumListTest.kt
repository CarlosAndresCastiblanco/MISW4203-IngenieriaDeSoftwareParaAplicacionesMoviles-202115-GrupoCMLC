package com.uniandes.vinilos.ui.activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uniandes.vinilos.DetailsScreen

import com.uniandes.vinilos.ListScreen
import com.uniandes.vinilos.MainActivity
import com.uniandes.vinilos.model.Performer
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
        //Release Date
        composeRule.onNodeWithText("1948-07-16").assertExists()


        //Validate return button OnClick
        composeRule.onNodeWithContentDescription("Back").performClick()
        // Validate Album List
        Thread.sleep(2000)
        composeRule.onNodeWithText("Album 1").assertExists()
        composeRule.onNodeWithText("Album 2").assertExists()
        Thread.sleep(1000)
    }
}