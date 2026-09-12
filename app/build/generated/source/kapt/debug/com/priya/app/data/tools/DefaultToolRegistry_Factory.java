package com.priya.app.data.tools;

import com.priya.app.domain.tools.AssistantTool;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Set;
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
public final class DefaultToolRegistry_Factory implements Factory<DefaultToolRegistry> {
  private final Provider<Set<AssistantTool>> registeredToolsProvider;

  public DefaultToolRegistry_Factory(Provider<Set<AssistantTool>> registeredToolsProvider) {
    this.registeredToolsProvider = registeredToolsProvider;
  }

  @Override
  public DefaultToolRegistry get() {
    return newInstance(registeredToolsProvider.get());
  }

  public static DefaultToolRegistry_Factory create(
      Provider<Set<AssistantTool>> registeredToolsProvider) {
    return new DefaultToolRegistry_Factory(registeredToolsProvider);
  }

  public static DefaultToolRegistry newInstance(Set<AssistantTool> registeredTools) {
    return new DefaultToolRegistry(registeredTools);
  }
}
