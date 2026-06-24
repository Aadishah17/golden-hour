package com.goldenhour.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldenhour.model.AmbulanceTelemetry
import com.goldenhour.model.EmergencySession
import com.goldenhour.repository.EmergencyRepository
import com.goldenhour.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val emergencyRepository: EmergencyRepository,
    languageRepository: LanguageRepository
) : ViewModel() {
    val selectedLanguage: StateFlow<String> = languageRepository.selectedLanguage
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "en")

    val session: StateFlow<EmergencySession> = emergencyRepository.session
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), EmergencySession())

    private val _ambulance = MutableStateFlow(AmbulanceTelemetry())
    val ambulance: StateFlow<AmbulanceTelemetry> = _ambulance.asStateFlow()

    private var trackingJob: Job? = null

    init {
        startTracking()
    }

    private fun startTracking() {
        if (trackingJob?.isActive == true) return
        trackingJob = viewModelScope.launch {
            emergencyRepository.ambulanceTelemetry().collect(_ambulance::emit)
        }
    }

    fun reset() {
        emergencyRepository.reset()
    }
}
