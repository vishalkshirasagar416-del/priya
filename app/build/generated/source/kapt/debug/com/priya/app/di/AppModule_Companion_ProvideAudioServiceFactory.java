package com.priya.app.di;

import com.priya.app.services.AudioService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_Companion_ProvideAudioServiceFactory implements Factory<AudioService> {
  @Override
  public AudioService get() {
    return provideAudioService();
  }

  public static AppModule_Companion_ProvideAudioServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AudioService provideAudioService() {
    return Preconditions.checkNotNullFromProvides(AppModule.Companion.provideAudioService());
  }

  private static final class InstanceHolder {
    private static final AppModule_Companion_ProvideAudioServiceFactory INSTANCE = new AppModule_Companion_ProvideAudioServiceFactory();
  }
}
