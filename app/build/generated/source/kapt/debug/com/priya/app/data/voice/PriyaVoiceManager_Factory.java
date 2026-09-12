package com.priya.app.data.voice;

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
public final class PriyaVoiceManager_Factory implements Factory<PriyaVoiceManager> {
  private final Provider<ElevenLabsTTSProvider> elevenLabsTTSProvider;

  private final Provider<KokoroTTSProvider> kokoroTTSProvider;

  private final Provider<LocalAndroidTTSProvider> localAndroidTTSProvider;

  private final Provider<VoicePreferences> voicePreferencesProvider;

  private final Provider<KokoroModelManager> kokoroModelManagerProvider;

  public PriyaVoiceManager_Factory(Provider<ElevenLabsTTSProvider> elevenLabsTTSProvider,
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
    return newInstance(elevenLabsTTSProvider.get(), kokoroTTSProvider.get(), localAndroidTTSProvider.get(), voicePreferencesProvider.get(), kokoroModelManagerProvider.get());
  }

  public static PriyaVoiceManager_Factory create(
      Provider<ElevenLabsTTSProvider> elevenLabsTTSProvider,
      Provider<KokoroTTSProvider> kokoroTTSProvider,
      Provider<LocalAndroidTTSProvider> localAndroidTTSProvider,
      Provider<VoicePreferences> voicePreferencesProvider,
      Provider<KokoroModelManager> kokoroModelManagerProvider) {
    return new PriyaVoiceManager_Factory(elevenLabsTTSProvider, kokoroTTSProvider, localAndroidTTSProvider, voicePreferencesProvider, kokoroModelManagerProvider);
  }

  public static PriyaVoiceManager newInstance(ElevenLabsTTSProvider elevenLabsTTSProvider,
      KokoroTTSProvider kokoroTTSProvider, LocalAndroidTTSProvider localAndroidTTSProvider,
      VoicePreferences voicePreferences, KokoroModelManager kokoroModelManager) {
    return new PriyaVoiceManager(elevenLabsTTSProvider, kokoroTTSProvider, localAndroidTTSProvider, voicePreferences, kokoroModelManager);
  }
}
