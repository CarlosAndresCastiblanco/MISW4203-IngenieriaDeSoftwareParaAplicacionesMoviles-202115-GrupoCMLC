package com.uniandes.vinilos.ui.activity

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uniandes.vinilos.*
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ArtistListTest {

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
            AlbumsAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = "artists"
                ) {
                    composable(route = "artists") {
                        ArtistsListScreen(navController = navController)
                    }
                    composable(route = "ARTIST_DETAIL_SCREEN/1") {
                        ArtistsDetailsScreen("1", navController = navController)
                    }

                }
            }
        }
    }

    @Test
    fun validateListArtistActivity() {
        Thread.sleep(500)
        composeRule.onNodeWithText("Lista de Artistas").assertExists()
        Thread.sleep(500)
        composeRule.onNodeWithText("Artista 1").assertExists()
        Thread.sleep(500)
        composeRule.onNodeWithText("Artista 2").assertExists()
    }

    @Test
    fun validateDetailArtistActivity() {
        Thread.sleep(500)
        composeRule.onNodeWithText("Artista 1").assertExists()
        //On click details
        composeRule.onNodeWithText("Artista 1").performClick()
        Thread.sleep(4000)
        //Artista
        composeRule.onNodeWithText("Artista 1").assertExists()
        //Descripcion
        composeRule.onNodeWithText("Artista del genero salsa").assertExists()
        //Validate return button OnClick
        composeRule.onNodeWithContentDescription("Back").performClick()
        // Validate Artist List
        Thread.sleep(2000)
        composeRule.onNodeWithText("Artista 1").assertExists()
        composeRule.onNodeWithText("Artista 2").assertExists()
        Thread.sleep(1000)
    }

}