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

    @Test
    fun `RTS scoring correctly calculates stable physiology`() {
        val stable = TriageData(
            isConscious = true,
            hasHeavyBleeding = false,
            isBreathing = true
        )
        // GCS (4) + RR (4) + SBP (4) = 12
        assertEquals(12, stable.rtsScore)
        assertEquals("Low Risk - Stable", stable.rtsSeverity)
    }

    @Test
    fun `RTS scoring correctly calculates critical physiology for CPR`() {
        val cprNeeded = TriageData(
            isConscious = false,
            hasHeavyBleeding = true,
            isBreathing = false
        )
        // GCS (0) + RR (0) + SBP (2) = 2
        assertEquals(2, cprNeeded.rtsScore)
        assertEquals("Extreme Risk - Resuscitation", cprNeeded.rtsSeverity)
    }

    @Test
    fun `RTS scoring correctly calculates moderate risk physiology`() {
        val moderate = TriageData(
            isConscious = true,
            hasHeavyBleeding = true,
            isBreathing = true
        )
        // GCS (4) + RR (4) + SBP (2) = 10
        assertEquals(10, moderate.rtsScore)
        assertEquals("Moderate Risk - Urgent", moderate.rtsSeverity)
    }
}
