package com.example.coursenavigator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class CourseNavigatorAppTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun startLearningOpensTopicList() {
        composeRule.onNodeWithText("Course Navigator").assertIsDisplayed()
        composeRule.onNodeWithText("Start learning").performClick()
        composeRule.onNodeWithText("Choose a topic").assertIsDisplayed()
    }
}
