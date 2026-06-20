package com.goldenhour.viewmodel;

import com.goldenhour.repository.EmergencyRepository;
import com.goldenhour.repository.LanguageRepository;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<EmergencyRepository> emergencyRepositoryProvider;

  private final Provider<LanguageRepository> languageRepositoryProvider;

  private DashboardViewModel_Factory(Provider<EmergencyRepository> emergencyRepositoryProvider,
      Provider<LanguageRepository> languageRepositoryProvider) {
    this.emergencyRepositoryProvider = emergencyRepositoryProvider;
    this.languageRepositoryProvider = languageRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(emergencyRepositoryProvider.get(), languageRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<EmergencyRepository> emergencyRepositoryProvider,
      Provider<LanguageRepository> languageRepositoryProvider) {
    return new DashboardViewModel_Factory(emergencyRepositoryProvider, languageRepositoryProvider);
  }

  public static DashboardViewModel newInstance(EmergencyRepository emergencyRepository,
      LanguageRepository languageRepository) {
    return new DashboardViewModel(emergencyRepository, languageRepository);
  }
}
