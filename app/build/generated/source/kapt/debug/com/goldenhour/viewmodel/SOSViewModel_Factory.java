package com.goldenhour.viewmodel;

import com.goldenhour.repository.EmergencyRepository;
import com.goldenhour.repository.LanguageRepository;
import com.goldenhour.utils.SoundEffects;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "KotlinInternalInJava"
})
public final class SOSViewModel_Factory implements Factory<SOSViewModel> {
  private final Provider<EmergencyRepository> emergencyRepositoryProvider;

  private final Provider<LanguageRepository> languageRepositoryProvider;

  private final Provider<SoundEffects> soundEffectsProvider;

  public SOSViewModel_Factory(Provider<EmergencyRepository> emergencyRepositoryProvider,
      Provider<LanguageRepository> languageRepositoryProvider,
      Provider<SoundEffects> soundEffectsProvider) {
    this.emergencyRepositoryProvider = emergencyRepositoryProvider;
    this.languageRepositoryProvider = languageRepositoryProvider;
    this.soundEffectsProvider = soundEffectsProvider;
  }

  @Override
  public SOSViewModel get() {
    return newInstance(emergencyRepositoryProvider.get(), languageRepositoryProvider.get(), soundEffectsProvider.get());
  }

  public static SOSViewModel_Factory create(
      Provider<EmergencyRepository> emergencyRepositoryProvider,
      Provider<LanguageRepository> languageRepositoryProvider,
      Provider<SoundEffects> soundEffectsProvider) {
    return new SOSViewModel_Factory(emergencyRepositoryProvider, languageRepositoryProvider, soundEffectsProvider);
  }

  public static SOSViewModel newInstance(EmergencyRepository emergencyRepository,
      LanguageRepository languageRepository, SoundEffects soundEffects) {
    return new SOSViewModel(emergencyRepository, languageRepository, soundEffects);
  }
}
