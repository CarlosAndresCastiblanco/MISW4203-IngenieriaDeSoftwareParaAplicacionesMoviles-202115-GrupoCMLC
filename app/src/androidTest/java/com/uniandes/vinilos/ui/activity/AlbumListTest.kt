package com.uniandes.vinilos.ui.activity

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.uniandes.vinilos.ListScreen
import com.uniandes.vinilos.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class AlbumListTest {
    @get:Rule var activityScenarioRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkActivityVisibility(){
        activityScenarioRule.onNodeWithText("Listado de Álbumes").assertExists()
        activityScenarioRule.onNodeWithText("Álbumes").assertExists()
        activityScenarioRule.onNodeWithTag("Card").assertHasClickAction()
    }

    @Test
    fun selectAnyAlbumList(){
        activityScenarioRule.onNodeWithText("Buscando").performClick()
        activityScenarioRule.onNodeWithText("100 - Buscando").assertExists()
        activityScenarioRule.onNodeWithText("Bellido de Luna").assertExists()
    }
}