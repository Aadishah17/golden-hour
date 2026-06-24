package com.goldenhour.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldenhour.model.TriageData
import com.goldenhour.repository.EmergencyRepository
import com.goldenhour.repository.LanguageRepository
import com.goldenhour.utils.SoundEffects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriageViewModel @Inject constructor(
    private val emergencyRepository: EmergencyRepository,
    languageRepository: LanguageRepository,
    private val soundEffects: SoundEffects
) : ViewModel() {
    val selectedLanguage: StateFlow<String> = languageRepository.selectedLanguage
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "en")

    private val _triage = MutableStateFlow(emergencyRepository.session.value.triage)
    val triage: StateFlow<TriageData> = _triage.asStateFlow()

    private val _questionIndex = MutableStateFlow(0)
    val questionIndex: StateFlow<Int> = _questionIndex.asStateFlow()

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

    private var voiceSimulationJob: Job? = null

    fun answer(answer: Boolean) {
        val updated = when (_questionIndex.value) {
            0 -> _triage.value.copy(isConscious = answer)
            1 -> _triage.value.copy(hasHeavyBleeding = answer)
            else -> _triage.value.copy(isBreathing = answer)
        }
        save(updated)
        soundEffects.selection()
        if (_questionIndex.value < LAST_QUESTION) {
            _questionIndex.value += 1
        }
    }

    fun previousQuestion() {
        _questionIndex.value = (_questionIndex.value - 1).coerceAtLeast(0)
    }

    fun updateReport(report: String) {
        save(_triage.value.copy(sceneReport = report.take(500)))
    }

    fun updateScenePhoto(bitmap: android.graphics.Bitmap) {
        save(_triage.value.copy(scenePhoto = bitmap))
    }

    fun toggleVoiceSimulation() {
        if (_isListening.value) {
            voiceSimulationJob?.cancel()
            _isListening.value = false
            return
        }
        voiceSimulationJob = viewModelScope.launch {
            _isListening.value = true
            updateReport("Listening to simulated scene report…")
            delay(900)
            updateReport("2 victims, one unconscious, car overturned near Kamothe Junction.")
            _isListening.value = false
        }
    }

    private fun save(updated: TriageData) {
        _triage.value = updated
        emergencyRepository.updateTriage(updated)
    }

    private companion object {
        const val LAST_QUESTION = 2
    }
}
