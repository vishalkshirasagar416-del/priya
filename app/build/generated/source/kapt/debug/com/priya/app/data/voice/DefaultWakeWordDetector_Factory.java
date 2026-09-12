package com.priya.app.data.voice;

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
    "cast"
})
public final class DefaultWakeWordDetector_Factory implements Factory<DefaultWakeWordDetector> {
  @Override
  public DefaultWakeWordDetector get() {
    return newInstance();
  }

  public static DefaultWakeWordDetector_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DefaultWakeWordDetector newInstance() {
    return new DefaultWakeWordDetector();
  }

  private static final class InstanceHolder {
    private static final DefaultWakeWordDetector_Factory INSTANCE = new DefaultWakeWordDetector_Factory();
  }
}
