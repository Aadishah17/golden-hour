package com.goldenhour.viewmodel;

import com.goldenhour.repository.LanguageRepository;
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
public final class GoodSamaritanViewModel_Factory implements Factory<GoodSamaritanViewModel> {
  private final Provider<LanguageRepository> languageRepositoryProvider;

  public GoodSamaritanViewModel_Factory(Provider<LanguageRepository> languageRepositoryProvider) {
    this.languageRepositoryProvider = languageRepositoryProvider;
  }

  @Override
  public GoodSamaritanViewModel get() {
    return newInstance(languageRepositoryProvider.get());
  }

  public static GoodSamaritanViewModel_Factory create(
      Provider<LanguageRepository> languageRepositoryProvider) {
    return new GoodSamaritanViewModel_Factory(languageRepositoryProvider);
  }

  public static GoodSamaritanViewModel newInstance(LanguageRepository languageRepository) {
    return new GoodSamaritanViewModel(languageRepository);
  }
}
