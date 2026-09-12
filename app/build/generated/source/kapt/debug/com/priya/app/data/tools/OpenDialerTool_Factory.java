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
public final class OpenDialerTool_Factory implements Factory<OpenDialerTool> {
  private final Provider<Context> contextProvider;

  public OpenDialerTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OpenDialerTool get() {
    return newInstance(contextProvider.get());
  }

  public static OpenDialerTool_Factory create(Provider<Context> contextProvider) {
    return new OpenDialerTool_Factory(contextProvider);
  }

  public static OpenDialerTool newInstance(Context context) {
    return new OpenDialerTool(context);
  }
}
