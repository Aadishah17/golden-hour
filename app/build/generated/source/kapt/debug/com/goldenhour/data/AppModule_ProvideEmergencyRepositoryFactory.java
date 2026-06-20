package com.goldenhour.data;

import com.goldenhour.repository.EmergencyRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideEmergencyRepositoryFactory implements Factory<EmergencyRepository> {
  @Override
  public EmergencyRepository get() {
    return provideEmergencyRepository();
  }

  public static AppModule_ProvideEmergencyRepositoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EmergencyRepository provideEmergencyRepository() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideEmergencyRepository());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideEmergencyRepositoryFactory INSTANCE = new AppModule_ProvideEmergencyRepositoryFactory();
  }
}
