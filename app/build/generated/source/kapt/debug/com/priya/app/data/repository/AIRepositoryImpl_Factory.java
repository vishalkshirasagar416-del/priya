package com.priya.app.data.repository;

import com.priya.app.data.ai.AIProviderManager;
import com.priya.app.domain.repository.ConversationRepository;
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
public final class AIRepositoryImpl_Factory implements Factory<AIRepositoryImpl> {
  private final Provider<AIProviderManager> providerManagerProvider;

  private final Provider<ConversationRepository> conversationRepositoryProvider;

  public AIRepositoryImpl_Factory(Provider<AIProviderManager> providerManagerProvider,
      Provider<ConversationRepository> conversationRepositoryProvider) {
    this.providerManagerProvider = providerManagerProvider;
    this.conversationRepositoryProvider = conversationRepositoryProvider;
  }

  @Override
  public AIRepositoryImpl get() {
    return newInstance(providerManagerProvider.get(), conversationRepositoryProvider.get());
  }

  public static AIRepositoryImpl_Factory create(Provider<AIProviderManager> providerManagerProvider,
      Provider<ConversationRepository> conversationRepositoryProvider) {
    return new AIRepositoryImpl_Factory(providerManagerProvider, conversationRepositoryProvider);
  }

  public static AIRepositoryImpl newInstance(AIProviderManager providerManager,
      ConversationRepository conversationRepository) {
    return new AIRepositoryImpl(providerManager, conversationRepository);
  }
}
