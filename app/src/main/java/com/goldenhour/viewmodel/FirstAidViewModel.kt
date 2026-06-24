package com.goldenhour.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldenhour.model.TriageData
import com.goldenhour.repository.EmergencyRepository
import com.goldenhour.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FirstAidViewModel @Inject constructor(
    private val emergencyRepository: EmergencyRepository,
    languageRepository: LanguageRepository
) : ViewModel() {
    val selectedLanguage: StateFlow<String> = languageRepository.selectedLanguage
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "en")

    val triage: StateFlow<TriageData> = emergencyRepository.session
        .map { it.triage }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), TriageData())
}
