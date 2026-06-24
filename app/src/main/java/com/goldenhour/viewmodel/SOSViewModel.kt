package com.goldenhour.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldenhour.model.EmergencySession
import com.goldenhour.model.VictimRange
import com.goldenhour.repository.EmergencyRepository
import com.goldenhour.repository.LanguageRepository
import com.goldenhour.utils.SoundEffects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SOSViewModel @Inject constructor(
    private val emergencyRepository: EmergencyRepository,
    languageRepository: LanguageRepository,
    private val soundEffects: SoundEffects
) : ViewModel() {
    val selectedLanguage: StateFlow<String> = languageRepository.selectedLanguage
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "en")

    val session: StateFlow<EmergencySession> = emergencyRepository.session
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), EmergencySession())

    fun selectVictimRange(range: VictimRange) {
        emergencyRepository.selectVictimRange(range)
        soundEffects.selection()
    }

    fun selectHospital(hospital: com.goldenhour.model.HospitalInfo) {
        emergencyRepository.selectHospital(hospital)
        soundEffects.selection()
    }

    fun updateLocation(context: android.content.Context, lat: Double, lng: Double, placeName: String) {
        emergencyRepository.updateLocationAndMatchHospitals(context, lat, lng, placeName)
    }

    fun currentLocationFlow(context: android.content.Context) = emergencyRepository.currentLocation(context)

    suspend fun reverseGeocode(lat: Double, lng: Double): String {
        return emergencyRepository.reverseGeocode(lat, lng)
    }

    fun activateSos() {
        soundEffects.alert()
    }
}
