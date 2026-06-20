package com.goldenhour.viewmodel;

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
public final class LanguageViewModel_Factory implements Factory<LanguageViewModel> {
  private final Provider<LanguageRepository> languageRepositoryProvider;

  private LanguageViewModel_Factory(Provider<LanguageRepository> languageRepositoryProvider) {
    this.languageRepositoryProvider = languageRepositoryProvider;
  }

  @Override
  public LanguageViewModel get() {
    return newInstance(languageRepositoryProvider.get());
  }

  public static LanguageViewModel_Factory create(
      Provider<LanguageRepository> languageRepositoryProvider) {
    return new LanguageViewModel_Factory(languageRepositoryProvider);
  }

  public static LanguageViewModel newInstance(LanguageRepository languageRepository) {
    return new LanguageViewModel(languageRepository);
  }
}
