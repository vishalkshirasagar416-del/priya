package com.priya.app.data.tools;

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
public final class SearchWebTool_Factory implements Factory<SearchWebTool> {
  private final Provider<Context> contextProvider;

  public SearchWebTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SearchWebTool get() {
    return newInstance(contextProvider.get());
  }

  public static SearchWebTool_Factory create(Provider<Context> contextProvider) {
    return new SearchWebTool_Factory(contextProvider);
  }

  public static SearchWebTool newInstance(Context context) {
    return new SearchWebTool(context);
  }
}
