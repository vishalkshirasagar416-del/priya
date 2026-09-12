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
public final class ElevenLabsTTSProvider_Factory implements Factory<ElevenLabsTTSProvider> {
  private final Provider<Context> contextProvider;

  public ElevenLabsTTSProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ElevenLabsTTSProvider get() {
    return newInstance(contextProvider.get());
  }

  public static ElevenLabsTTSProvider_Factory create(Provider<Context> contextProvider) {
    return new ElevenLabsTTSProvider_Factory(contextProvider);
  }

  public static ElevenLabsTTSProvider newInstance(Context context) {
    return new ElevenLabsTTSProvider(context);
  }
}
