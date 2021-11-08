package com.uniandes.vinilos.ui.activity

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.uniandes.vinilos.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumDetailTest {
    @get:Rule
    var activityScenarioRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkBackButtonAlbumDetail(){
        activityScenarioRule.onNodeWithText("Buscando").performClick()
        activityScenarioRule.onNodeWithTag("IconButton").assertHasClickAction()
        activityScenarioRule.onNodeWithTag("IconButton").performClick()
        activityScenarioRule.onNodeWithText("Listado de Álbumes").assertExists()
    }

    @Test
    fun checkTextAlbumDetail(){
        activityScenarioRule.onNodeWithText("Buscando").performClick()
        activityScenarioRule.onNodeWithText("100 - Buscando").assertExists()
        activityScenarioRule.onNodeWithText("Titulo").assertExists()
        activityScenarioRule.onNodeWithText("Artista").assertExists()
        activityScenarioRule.onNodeWithText("Genero").assertExists()

    }

}