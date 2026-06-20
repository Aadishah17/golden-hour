package com.goldenhour.viewmodel;

import com.goldenhour.repository.EmergencyRepository;
import com.goldenhour.repository.LanguageRepository;
import com.goldenhour.utils.SoundEffects;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class TriageViewModel_Factory implements Factory<TriageViewModel> {
  private final Provider<EmergencyRepository> emergencyRepositoryProvider;

  private final Provider<LanguageRepository> languageRepositoryProvider;

  private final Provider<SoundEffects> soundEffectsProvider;

  private TriageViewModel_Factory(Provider<EmergencyRepository> emergencyRepositoryProvider,
      Provider<LanguageRepository> languageRepositoryProvider,
      Provider<SoundEffects> soundEffectsProvider) {
    this.emergencyRepositoryProvider = emergencyRepositoryProvider;
    this.languageRepositoryProvider = languageRepositoryProvider;
    this.soundEffectsProvider = soundEffectsProvider;
  }

  @Override
  public TriageViewModel get() {
    return newInstance(emergencyRepositoryProvider.get(), languageRepositoryProvider.get(), soundEffectsProvider.get());
  }

  public static TriageViewModel_Factory create(
      Provider<EmergencyRepository> emergencyRepositoryProvider,
      Provider<LanguageRepository> languageRepositoryProvider,
      Provider<SoundEffects> soundEffectsProvider) {
    return new TriageViewModel_Factory(emergencyRepositoryProvider, languageRepositoryProvider, soundEffectsProvider);
  }

  public static TriageViewModel newInstance(EmergencyRepository emergencyRepository,
      LanguageRepository languageRepository, SoundEffects soundEffects) {
    return new TriageViewModel(emergencyRepository, languageRepository, soundEffects);
  }
}
