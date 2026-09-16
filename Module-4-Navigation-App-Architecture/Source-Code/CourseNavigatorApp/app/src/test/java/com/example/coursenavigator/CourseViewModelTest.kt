package com.example.coursenavigator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CourseViewModelTest {
    @Test
    fun selectTopic_updatesStateForKnownTopic() {
        val viewModel = CourseViewModel()

        viewModel.selectTopic(2)

        assertEquals(2, viewModel.uiState.value.selectedTopicId)
    }

    @Test
    fun selectTopic_ignoresUnknownTopic() {
        val viewModel = CourseViewModel()

        viewModel.selectTopic(99)

        assertNull(viewModel.uiState.value.selectedTopicId)
    }
}
