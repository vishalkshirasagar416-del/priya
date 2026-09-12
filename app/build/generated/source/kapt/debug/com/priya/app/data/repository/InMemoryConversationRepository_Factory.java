package com.priya.app.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class InMemoryConversationRepository_Factory implements Factory<InMemoryConversationRepository> {
  @Override
  public InMemoryConversationRepository get() {
    return newInstance();
  }

  public static InMemoryConversationRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InMemoryConversationRepository newInstance() {
    return new InMemoryConversationRepository();
  }

  private static final class InstanceHolder {
    private static final InMemoryConversationRepository_Factory INSTANCE = new InMemoryConversationRepository_Factory();
  }
}
