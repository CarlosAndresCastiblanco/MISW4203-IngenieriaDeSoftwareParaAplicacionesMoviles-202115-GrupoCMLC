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
class CollectorListTest {

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
                    startDestination = "collectors"
                ) {
                    composable(route = "collectors") {
                        CollectorListScreen(navController = navController)
                    }
                    composable(route = "COLLECTOR_DETAILS_SCREEN/1") {
                        CollectorDetailsScreen("1", navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun validateCollectorListActivity() {
        Thread.sleep(500)
        composeRule.onNodeWithText("Lista de Coleccionistas").assertExists()
        Thread.sleep(500)
        composeRule.onNodeWithText("Collecionista 1").assertExists()
        composeRule.onNodeWithText("1234567").assertExists()
        composeRule.onNodeWithText("coleccionista1@vinilos.com").assertExists()
        Thread.sleep(500)
        composeRule.onNodeWithText("Collecionista 2").assertExists()
        composeRule.onNodeWithText("7654321").assertExists()
        composeRule.onNodeWithText("coleccionista2@vinilos.com").assertExists()    }

    @Test
    fun validateDetailAlbumActivity() {
        Thread.sleep(500)
        composeRule.onNodeWithText("Collecionista 1").assertExists()
        //On click details
        composeRule.onNodeWithText("Collecionista 1").performClick()
        Thread.sleep(2000)
        //Artistas Favoritos
        composeRule.onNodeWithText("Artista 1").assertExists()
        composeRule.onNodeWithText("Artista 2").assertExists()

        //Albumes Coleccionista
        composeRule.onNodeWithText("Album 1").assertExists()
        composeRule.onNodeWithText("$123").assertExists()
        composeRule.onNodeWithText("Album 2").assertExists()
        composeRule.onNodeWithText("$456").assertExists()


        //Validate return button OnClick
        composeRule.onNodeWithContentDescription("Back").performClick()
        // Validate Album List
        Thread.sleep(2000)
        composeRule.onNodeWithText("Collecionista 1").assertExists()
        composeRule.onNodeWithText("Collecionista 2").assertExists()
        Thread.sleep(1000)
    }
}