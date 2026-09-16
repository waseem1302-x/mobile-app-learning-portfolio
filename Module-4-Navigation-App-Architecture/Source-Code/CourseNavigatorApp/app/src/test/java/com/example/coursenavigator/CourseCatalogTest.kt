package com.example.coursenavigator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CourseCatalogTest {
    @Test
    fun find_returnsMatchingTopic() {
        assertEquals("Navigation Compose", CourseCatalog.find(2)?.title)
    }

    @Test
    fun find_returnsNullForUnknownTopic() {
        assertNull(CourseCatalog.find(999))
    }

    @Test
    fun expandedLayout_startsAtSevenHundredDp() {
        assertFalse(usesExpandedLayout(699f))
        assertTrue(usesExpandedLayout(700f))
    }
}
