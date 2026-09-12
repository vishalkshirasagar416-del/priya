package com.priya.app.data.voice;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class LocalAndroidTTSProvider_Factory implements Factory<LocalAndroidTTSProvider> {
  private final Provider<Context> contextProvider;

  public LocalAndroidTTSProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocalAndroidTTSProvider get() {
    return newInstance(contextProvider.get());
  }

  public static LocalAndroidTTSProvider_Factory create(Provider<Context> contextProvider) {
    return new LocalAndroidTTSProvider_Factory(contextProvider);
  }

  public static LocalAndroidTTSProvider newInstance(Context context) {
    return new LocalAndroidTTSProvider(context);
  }
}
