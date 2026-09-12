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
public final class KokoroOnnxRuntimeBridge_Factory implements Factory<KokoroOnnxRuntimeBridge> {
  private final Provider<KokoroModelManager> modelManagerProvider;

  public KokoroOnnxRuntimeBridge_Factory(Provider<KokoroModelManager> modelManagerProvider) {
    this.modelManagerProvider = modelManagerProvider;
  }

  @Override
  public KokoroOnnxRuntimeBridge get() {
    return newInstance(modelManagerProvider.get());
  }

  public static KokoroOnnxRuntimeBridge_Factory create(
      Provider<KokoroModelManager> modelManagerProvider) {
    return new KokoroOnnxRuntimeBridge_Factory(modelManagerProvider);
  }

  public static KokoroOnnxRuntimeBridge newInstance(KokoroModelManager modelManager) {
    return new KokoroOnnxRuntimeBridge(modelManager);
  }
}
