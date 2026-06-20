package com.goldenhour.data;

import android.content.Context;
import com.goldenhour.repository.LanguageRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideLanguageRepositoryFactory implements Factory<LanguageRepository> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideLanguageRepositoryFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LanguageRepository get() {
    return provideLanguageRepository(contextProvider.get());
  }

  public static AppModule_ProvideLanguageRepositoryFactory create(
      Provider<Context> contextProvider) {
    return new AppModule_ProvideLanguageRepositoryFactory(contextProvider);
  }

  public static LanguageRepository provideLanguageRepository(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideLanguageRepository(context));
  }
}
