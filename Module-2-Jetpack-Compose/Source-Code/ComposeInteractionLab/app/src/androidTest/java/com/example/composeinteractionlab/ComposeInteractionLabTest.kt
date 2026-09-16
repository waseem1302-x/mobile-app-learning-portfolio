package com.example.composeinteractionlab

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class ComposeInteractionLabTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appDisplaysBothInteractiveExercises() {
        composeRule.onNodeWithText("Compose Interaction Lab").assertIsDisplayed()
        composeRule.onNodeWithText("Dice Roller").assertIsDisplayed()
        composeRule.onNodeWithText("Tip Calculator").assertIsDisplayed()
    }
}
