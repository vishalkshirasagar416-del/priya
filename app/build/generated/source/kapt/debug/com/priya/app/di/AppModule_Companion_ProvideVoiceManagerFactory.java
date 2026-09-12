package com.priya.app.di;

import com.priya.app.data.voice.ElevenLabsTTSProvider;
import com.priya.app.data.voice.KokoroModelManager;
import com.priya.app.data.voice.KokoroTTSProvider;
import com.priya.app.data.voice.LocalAndroidTTSProvider;
import com.priya.app.data.voice.PriyaVoiceManager;
import com.priya.app.data.voice.VoicePreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_Companion_ProvideVoiceManagerFactory implements Factory<PriyaVoiceManager> {
  private final Provider<ElevenLabsTTSProvider> elevenLabsTTSProvider;

  private final Provider<KokoroTTSProvider> kokoroTTSProvider;

  private final Provider<LocalAndroidTTSProvider> localAndroidTTSProvider;

  private final Provider<VoicePreferences> voicePreferencesProvider;

  private final Provider<KokoroModelManager> kokoroModelManagerProvider;

  public AppModule_Companion_ProvideVoiceManagerFactory(
      Provider<ElevenLabsTTSProvider> elevenLabsTTSProvider,
      Provider<KokoroTTSProvider> kokoroTTSProvider,
      Provider<LocalAndroidTTSProvider> localAndroidTTSProvider,
      Provider<VoicePreferences> voicePreferencesProvider,
      Provider<KokoroModelManager> kokoroModelManagerProvider) {
    this.elevenLabsTTSProvider = elevenLabsTTSProvider;
    this.kokoroTTSProvider = kokoroTTSProvider;
    this.localAndroidTTSProvider = localAndroidTTSProvider;
    this.voicePreferencesProvider = voicePreferencesProvider;
    this.kokoroModelManagerProvider = kokoroModelManagerProvider;
  }

  @Override
  public PriyaVoiceManager get() {
    return provideVoiceManager(elevenLabsTTSProvider.get(), kokoroTTSProvider.get(), localAndroidTTSProvider.get(), voicePreferencesProvider.get(), kokoroModelManagerProvider.get());
  }

  public static AppModule_Companion_ProvideVoiceManagerFactory create(
      Provider<ElevenLabsTTSProvider> elevenLabsTTSProvider,
      Provider<KokoroTTSProvider> kokoroTTSProvider,
      Provider<LocalAndroidTTSProvider> localAndroidTTSProvider,
      Provider<VoicePreferences> voicePreferencesProvider,
      Provider<KokoroModelManager> kokoroModelManagerProvider) {
    return new AppModule_Companion_ProvideVoiceManagerFactory(elevenLabsTTSProvider, kokoroTTSProvider, localAndroidTTSProvider, voicePreferencesProvider, kokoroModelManagerProvider);
  }

  public static PriyaVoiceManager provideVoiceManager(ElevenLabsTTSProvider elevenLabsTTSProvider,
      KokoroTTSProvider kokoroTTSProvider, LocalAndroidTTSProvider localAndroidTTSProvider,
      VoicePreferences voicePreferences, KokoroModelManager kokoroModelManager) {
    return Preconditions.checkNotNullFromProvides(AppModule.Companion.provideVoiceManager(elevenLabsTTSProvider, kokoroTTSProvider, localAndroidTTSProvider, voicePreferences, kokoroModelManager));
  }
}
