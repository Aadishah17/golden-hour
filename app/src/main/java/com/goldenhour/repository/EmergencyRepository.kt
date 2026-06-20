package com.goldenhour.repository

import com.goldenhour.model.AmbulanceTelemetry
import com.goldenhour.model.EmergencySession
import com.goldenhour.model.TriageData
import com.goldenhour.model.VictimRange
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmergencyRepository @Inject constructor() {
    private val _session = MutableStateFlow(EmergencySession())
    val session = _session.asStateFlow()

    fun selectVictimRange(range: VictimRange) {
        _session.value = _session.value.copy(victimRange = range)
    }

    fun updateTriage(triageData: TriageData) {
        _session.value = _session.value.copy(triage = triageData)
    }

    fun reset() {
        _session.value = EmergencySession()
    }

    fun ambulanceTelemetry(): Flow<AmbulanceTelemetry> = flow {
        val route = listOf(
            "MGM Ambulance Bay",
            "Sector 12, Kamothe",
            "Kamothe Flyover",
            "Sector 7, Kamothe",
            "NH-66 Junction",
            "Accident Site"
        )

        for (step in 0..100 step 2) {
            val routeIndex = ((step / 100f) * route.lastIndex)
                .toInt()
                .coerceIn(route.indices)
            emit(
                AmbulanceTelemetry(
                    progress = step / 100f,
                    etaMinutes = if (step == 100) 0 else (12 - (step * 12 / 100)).coerceAtLeast(1),
                    currentLocation = route[routeIndex],
                    speedKph = if (step == 0 || step == 100) 0 else 42 + (step % 9)
                )
            )
            delay(900)
        }
    }
}
