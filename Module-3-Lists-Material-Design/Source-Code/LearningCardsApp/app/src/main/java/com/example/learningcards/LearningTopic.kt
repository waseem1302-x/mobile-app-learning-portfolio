package com.example.learningcards

data class LearningTopic(
    val id: Int,
    val title: String,
    val summary: String,
    val detail: String,
    val accent: Long,
)

fun learningTopics(): List<LearningTopic> = listOf(
    LearningTopic(1, "Compose state", "Build responsive interfaces from observable data.", "Compose turns state into UI through recomposition.", 0xFF365E9D),
    LearningTopic(2, "Lazy lists", "Render only the visible items in a long collection.", "LazyColumn keeps scrolling lists efficient and easy to structure.", 0xFF4E6356),
    LearningTopic(3, "Material theming", "Apply a consistent visual language across screens.", "MaterialTheme centralizes color, typography and shape decisions.", 0xFF67587A),
    LearningTopic(4, "Animation", "Guide attention with purposeful motion.", "AnimatedVisibility communicates changes without distracting the user.", 0xFF805157),
    LearningTopic(5, "Accessibility", "Make content understandable and operable for everyone.", "Semantics, contrast and meaningful labels improve inclusive experiences.", 0xFF27636A),
    LearningTopic(6, "Testing", "Protect user-visible behavior with automated checks.", "Compose tests locate semantic nodes and verify real interactions.", 0xFF735C00),
)
