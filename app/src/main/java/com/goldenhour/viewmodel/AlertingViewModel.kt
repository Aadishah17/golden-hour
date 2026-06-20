package com.goldenhour.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class AlertingViewModel @Inject constructor(
    languageRepository: LanguageRepository,
    private val soundEffects: SoundEffects
) : ViewModel() {
    val selectedLanguage: StateFlow<String> = languageRepository.selectedLanguage
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "en")

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress.asStateFlow()

    private val _completedSteps = MutableStateFlow(0)
    val completedSteps: StateFlow<Int> = _completedSteps.asStateFlow()

    private val _finished = MutableStateFlow(false)
    val finished: StateFlow<Boolean> = _finished.asStateFlow()

    private var simulationJob: Job? = null

    fun startSimulation() {
        if (simulationJob?.isActive == true || _finished.value) return
        simulationJob = viewModelScope.launch {
            repeat(100) { index ->
                delay(32)
                val value = (index + 1) / 100f
                _progress.value = value
                val completed = when {
                    value >= 1f -> 5
                    value >= .8f -> 4
                    value >= .6f -> 3
                    value >= .4f -> 2
                    value >= .2f -> 1
                    else -> 0
                }
                if (completed > _completedSteps.value) soundEffects.selection()
                _completedSteps.value = completed
            }
            delay(450)
            soundEffects.success()
            _finished.value = true
        }
    }
}
