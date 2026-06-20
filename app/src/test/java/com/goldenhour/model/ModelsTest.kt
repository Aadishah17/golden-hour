package com.goldenhour.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ModelsTest {
    @Test
    fun `triage is complete after three observations`() {
        val triage = TriageData(
            isConscious = true,
            hasHeavyBleeding = true,
            isBreathing = true
        )

        assertEquals(3, triage.answeredCount)
        assertTrue(triage.isComplete)
    }

    @Test
    fun `victim ranges match requested labels and simulated counts`() {
        assertEquals("1", VictimRange.ONE.label)
        assertEquals("2–5", VictimRange.TWO_TO_FIVE.label)
        assertEquals("5+", VictimRange.FIVE_PLUS.label)
        assertEquals(6, VictimRange.FIVE_PLUS.storedCount)
    }

    @Test
    fun `default emergency session uses simulated Kamothe MGM data`() {
        val session = EmergencySession()

        assertEquals("Kamothe, Raigad", session.location.placeName)
        assertEquals("18.9156° N, 73.0987° E", session.location.coordinates)
        assertEquals("MGM Hospital, Kamothe", session.hospital.name)
        assertEquals("Level I Trauma Centre", session.hospital.traumaLevel)
    }
}
