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
public final class OpenWhatsAppTool_Factory implements Factory<OpenWhatsAppTool> {
  private final Provider<Context> contextProvider;

  public OpenWhatsAppTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OpenWhatsAppTool get() {
    return newInstance(contextProvider.get());
  }

  public static OpenWhatsAppTool_Factory create(Provider<Context> contextProvider) {
    return new OpenWhatsAppTool_Factory(contextProvider);
  }

  public static OpenWhatsAppTool newInstance(Context context) {
    return new OpenWhatsAppTool(context);
  }
}
