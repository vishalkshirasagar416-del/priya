package com.priya.app.data.voice;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
    "KotlinInternalInJava",
    "cast"
})
public final class VoicePreferences_Factory implements Factory<VoicePreferences> {
  private final Provider<Context> contextProvider;

  public VoicePreferences_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public VoicePreferences get() {
    return newInstance(contextProvider.get());
  }

  public static VoicePreferences_Factory create(Provider<Context> contextProvider) {
    return new VoicePreferences_Factory(contextProvider);
  }

  public static VoicePreferences newInstance(Context context) {
    return new VoicePreferences(context);
  }
}
