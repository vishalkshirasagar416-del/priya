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
public final class KokoroTTSProvider_Factory implements Factory<KokoroTTSProvider> {
  private final Provider<Context> contextProvider;

  private final Provider<KokoroModelManager> modelManagerProvider;

  private final Provider<VoicePreferences> voicePreferencesProvider;

  private final Provider<KokoroOnnxRuntimeBridge> onnxBridgeProvider;

  public KokoroTTSProvider_Factory(Provider<Context> contextProvider,
      Provider<KokoroModelManager> modelManagerProvider,
      Provider<VoicePreferences> voicePreferencesProvider,
      Provider<KokoroOnnxRuntimeBridge> onnxBridgeProvider) {
    this.contextProvider = contextProvider;
    this.modelManagerProvider = modelManagerProvider;
    this.voicePreferencesProvider = voicePreferencesProvider;
    this.onnxBridgeProvider = onnxBridgeProvider;
  }

  @Override
  public KokoroTTSProvider get() {
    return newInstance(contextProvider.get(), modelManagerProvider.get(), voicePreferencesProvider.get(), onnxBridgeProvider.get());
  }

  public static KokoroTTSProvider_Factory create(Provider<Context> contextProvider,
      Provider<KokoroModelManager> modelManagerProvider,
      Provider<VoicePreferences> voicePreferencesProvider,
      Provider<KokoroOnnxRuntimeBridge> onnxBridgeProvider) {
    return new KokoroTTSProvider_Factory(contextProvider, modelManagerProvider, voicePreferencesProvider, onnxBridgeProvider);
  }

  public static KokoroTTSProvider newInstance(Context context, KokoroModelManager modelManager,
      VoicePreferences voicePreferences, KokoroOnnxRuntimeBridge onnxBridge) {
    return new KokoroTTSProvider(context, modelManager, voicePreferences, onnxBridge);
  }
}
