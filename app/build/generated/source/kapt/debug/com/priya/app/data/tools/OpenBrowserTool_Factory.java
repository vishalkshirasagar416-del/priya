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
public final class OpenBrowserTool_Factory implements Factory<OpenBrowserTool> {
  private final Provider<Context> contextProvider;

  public OpenBrowserTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OpenBrowserTool get() {
    return newInstance(contextProvider.get());
  }

  public static OpenBrowserTool_Factory create(Provider<Context> contextProvider) {
    return new OpenBrowserTool_Factory(contextProvider);
  }

  public static OpenBrowserTool newInstance(Context context) {
    return new OpenBrowserTool(context);
  }
}
