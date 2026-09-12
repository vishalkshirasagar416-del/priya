package com.priya.app.presentation.viewmodel;

import com.priya.app.data.voice.KokoroModelManager;
import com.priya.app.data.voice.VoicePreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class VoiceSettingsViewModel_Factory implements Factory<VoiceSettingsViewModel> {
  private final Provider<VoicePreferences> preferencesProvider;

  private final Provider<KokoroModelManager> kokoroModelManagerProvider;

  public VoiceSettingsViewModel_Factory(Provider<VoicePreferences> preferencesProvider,
      Provider<KokoroModelManager> kokoroModelManagerProvider) {
    this.preferencesProvider = preferencesProvider;
    this.kokoroModelManagerProvider = kokoroModelManagerProvider;
  }

  @Override
  public VoiceSettingsViewModel get() {
    return newInstance(preferencesProvider.get(), kokoroModelManagerProvider.get());
  }

  public static VoiceSettingsViewModel_Factory create(
      Provider<VoicePreferences> preferencesProvider,
      Provider<KokoroModelManager> kokoroModelManagerProvider) {
    return new VoiceSettingsViewModel_Factory(preferencesProvider, kokoroModelManagerProvider);
  }

  public static VoiceSettingsViewModel newInstance(VoicePreferences preferences,
      KokoroModelManager kokoroModelManager) {
    return new VoiceSettingsViewModel(preferences, kokoroModelManager);
  }
}
