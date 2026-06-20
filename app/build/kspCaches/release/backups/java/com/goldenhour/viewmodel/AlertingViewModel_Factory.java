package com.goldenhour.viewmodel;

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
public final class AlertingViewModel_Factory implements Factory<AlertingViewModel> {
  private final Provider<LanguageRepository> languageRepositoryProvider;

  private final Provider<SoundEffects> soundEffectsProvider;

  private AlertingViewModel_Factory(Provider<LanguageRepository> languageRepositoryProvider,
      Provider<SoundEffects> soundEffectsProvider) {
    this.languageRepositoryProvider = languageRepositoryProvider;
    this.soundEffectsProvider = soundEffectsProvider;
  }

  @Override
  public AlertingViewModel get() {
    return newInstance(languageRepositoryProvider.get(), soundEffectsProvider.get());
  }

  public static AlertingViewModel_Factory create(
      Provider<LanguageRepository> languageRepositoryProvider,
      Provider<SoundEffects> soundEffectsProvider) {
    return new AlertingViewModel_Factory(languageRepositoryProvider, soundEffectsProvider);
  }

  public static AlertingViewModel newInstance(LanguageRepository languageRepository,
      SoundEffects soundEffects) {
    return new AlertingViewModel(languageRepository, soundEffects);
  }
}
