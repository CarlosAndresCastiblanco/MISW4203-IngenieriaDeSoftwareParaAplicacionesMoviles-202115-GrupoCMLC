package com.uniandes.vinilos.ui.activity

import android.media.JetPlayer
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.uniandes.vinilos.HomeScreen
import com.uniandes.vinilos.ListScreen
import com.uniandes.vinilos.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class AlbumListTest {
    @get:Rule var activityScenarioRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navController : TestNavHostController
    @get:Rule val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun checkActivityVisibility(){
        activityScenarioRule.onNodeWithText("Lista de Álbumes").assertExists()
    }

    @Test
    fun selectAnyAlbumList(){
        composeTestRule.setContent { HomeScreen() }
        Thread.sleep(3000)
        composeTestRule.onNodeWithText("Buscando América").assertExists()

        //activityScenarioRule.onNodeWithTag("albumItem").performClick()
        //activityScenarioRule.onNodeWithText("Artista").assertExists()
        //activityScenarioRule.onNodeWithText("Bellido de Luna").assertExists()
    }
}