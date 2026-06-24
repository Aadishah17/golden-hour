package com.goldenhour.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.goldenhour.data.appDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    val selectedLanguage: Flow<String> = context.appDataStore.data
        .catch { error ->
            if (error is IOException) {
                emit(androidx.datastore.preferences.core.emptyPreferences())
            } else {
                throw error
            }
        }
        .map { preferences -> preferences[languageKey] ?: "en" }

    val isLanguageSaved: Flow<Boolean?> = context.appDataStore.data
        .map { preferences -> preferences.contains(languageKey) as Boolean? }
        .catch { error ->
            if (error is IOException) {
                emit(null)
            } else {
                throw error
            }
        }

    suspend fun saveLanguage(languageCode: String) {
        context.appDataStore.edit { preferences ->
            preferences[languageKey] = languageCode.takeIf { it in supportedLanguages } ?: "en"
        }
    }

    private companion object {
        val languageKey = stringPreferencesKey("selected_language")
        val supportedLanguages = setOf("en", "hi", "mr")
    }
}
