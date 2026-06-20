package com.goldenhour.utils;

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
    "KotlinInternalInJava"
})
public final class SoundEffects_Factory implements Factory<SoundEffects> {
  @Override
  public SoundEffects get() {
    return newInstance();
  }

  public static SoundEffects_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SoundEffects newInstance() {
    return new SoundEffects();
  }

  private static final class InstanceHolder {
    private static final SoundEffects_Factory INSTANCE = new SoundEffects_Factory();
  }
}
