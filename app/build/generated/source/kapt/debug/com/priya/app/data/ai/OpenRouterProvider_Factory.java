package com.priya.app.data.ai;

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
public final class OpenRouterProvider_Factory implements Factory<OpenRouterProvider> {
  @Override
  public OpenRouterProvider get() {
    return newInstance();
  }

  public static OpenRouterProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OpenRouterProvider newInstance() {
    return new OpenRouterProvider();
  }

  private static final class InstanceHolder {
    private static final OpenRouterProvider_Factory INSTANCE = new OpenRouterProvider_Factory();
  }
}
