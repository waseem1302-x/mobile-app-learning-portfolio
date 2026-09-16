package com.example.coursenavigator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CourseUiState(val selectedTopicId: Int? = null)

class CourseViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

    fun selectTopic(topicId: Int) {
        if (CourseCatalog.find(topicId) != null) {
            _uiState.update { it.copy(selectedTopicId = topicId) }
        }
    }
}
