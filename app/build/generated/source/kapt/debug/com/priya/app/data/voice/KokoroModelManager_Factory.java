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
public final class KokoroModelManager_Factory implements Factory<KokoroModelManager> {
  private final Provider<Context> contextProvider;

  public KokoroModelManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public KokoroModelManager get() {
    return newInstance(contextProvider.get());
  }

  public static KokoroModelManager_Factory create(Provider<Context> contextProvider) {
    return new KokoroModelManager_Factory(contextProvider);
  }

  public static KokoroModelManager newInstance(Context context) {
    return new KokoroModelManager(context);
  }
}
