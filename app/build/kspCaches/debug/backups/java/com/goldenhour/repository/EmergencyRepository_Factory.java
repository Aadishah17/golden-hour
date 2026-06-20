package com.goldenhour.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class EmergencyRepository_Factory implements Factory<EmergencyRepository> {
  @Override
  public EmergencyRepository get() {
    return newInstance();
  }

  public static EmergencyRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EmergencyRepository newInstance() {
    return new EmergencyRepository();
  }

  private static final class InstanceHolder {
    static final EmergencyRepository_Factory INSTANCE = new EmergencyRepository_Factory();
  }
}
