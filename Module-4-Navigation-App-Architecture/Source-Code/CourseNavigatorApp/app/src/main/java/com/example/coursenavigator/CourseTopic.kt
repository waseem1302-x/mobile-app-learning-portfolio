package com.example.coursenavigator

data class CourseTopic(
    val id: Int,
    val title: String,
    val summary: String,
    val outcomes: List<String>,
)

object CourseCatalog {
    val topics = listOf(
        CourseTopic(1, "Architecture Components", "Separate UI state from rendering logic.", listOf("ViewModel", "StateFlow", "Unidirectional data flow")),
        CourseTopic(2, "Navigation Compose", "Move between destinations and pass route arguments.", listOf("NavHost", "Routes", "Back stack")),
        CourseTopic(3, "Adaptive layouts", "Respond to compact and expanded window sizes.", listOf("Window width", "List-detail layout", "Responsive UI")),
    )

    fun find(id: Int): CourseTopic? = topics.firstOrNull { it.id == id }
}
