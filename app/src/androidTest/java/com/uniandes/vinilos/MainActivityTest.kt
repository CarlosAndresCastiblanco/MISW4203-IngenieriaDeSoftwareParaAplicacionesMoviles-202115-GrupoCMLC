package com.uniandes.vinilos

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityScenarioRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkActivityVisibility(){
        activityScenarioRule.onNodeWithText("Álbumes").assertExists()
    }
}