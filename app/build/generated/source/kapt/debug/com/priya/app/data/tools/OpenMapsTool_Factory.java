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
public final class OpenMapsTool_Factory implements Factory<OpenMapsTool> {
  private final Provider<Context> contextProvider;

  public OpenMapsTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OpenMapsTool get() {
    return newInstance(contextProvider.get());
  }

  public static OpenMapsTool_Factory create(Provider<Context> contextProvider) {
    return new OpenMapsTool_Factory(contextProvider);
  }

  public static OpenMapsTool newInstance(Context context) {
    return new OpenMapsTool(context);
  }
}
