package com.example.learningcards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class LearningCardsAppTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun firstCardCanExpand() {
        composeRule.onNodeWithText("Android Learning Cards").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Show details for Compose state").assertIsDisplayed()
        composeRule.onAllNodesWithText("Show details").onFirst().performClick()
        composeRule.onNodeWithText("Compose turns state into UI through recomposition.").assertIsDisplayed()
    }
}
