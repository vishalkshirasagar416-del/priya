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
public final class AndroidSpeechToTextProvider_Factory implements Factory<AndroidSpeechToTextProvider> {
  private final Provider<Context> contextProvider;

  public AndroidSpeechToTextProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AndroidSpeechToTextProvider get() {
    return newInstance(contextProvider.get());
  }

  public static AndroidSpeechToTextProvider_Factory create(Provider<Context> contextProvider) {
    return new AndroidSpeechToTextProvider_Factory(contextProvider);
  }

  public static AndroidSpeechToTextProvider newInstance(Context context) {
    return new AndroidSpeechToTextProvider(context);
  }
}
