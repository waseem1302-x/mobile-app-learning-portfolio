package com.example.learningcards

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningCatalogTest {
    @Test
    fun learningTopics_containsSixUniqueEntries() {
        val topics = learningTopics()
        assertEquals(6, topics.size)
        assertEquals(topics.size, topics.map { it.id }.distinct().size)
    }

    @Test
    fun learningTopics_hasCompleteDisplayContent() {
        assertTrue(learningTopics().all { it.title.isNotBlank() && it.summary.isNotBlank() && it.detail.isNotBlank() })
    }
}
