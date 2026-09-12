package com.priya.app.data.ai;

import com.priya.app.domain.ai.AIProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class AIProviderManager_Factory implements Factory<AIProviderManager> {
  private final Provider<AIProvider> geminiProvider;

  private final Provider<AIProvider> openRouterProvider;

  public AIProviderManager_Factory(Provider<AIProvider> geminiProvider,
      Provider<AIProvider> openRouterProvider) {
    this.geminiProvider = geminiProvider;
    this.openRouterProvider = openRouterProvider;
  }

  @Override
  public AIProviderManager get() {
    return newInstance(geminiProvider.get(), openRouterProvider.get());
  }

  public static AIProviderManager_Factory create(Provider<AIProvider> geminiProvider,
      Provider<AIProvider> openRouterProvider) {
    return new AIProviderManager_Factory(geminiProvider, openRouterProvider);
  }

  public static AIProviderManager newInstance(AIProvider geminiProvider,
      AIProvider openRouterProvider) {
    return new AIProviderManager(geminiProvider, openRouterProvider);
  }
}
