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
public final class OpenCalendarTool_Factory implements Factory<OpenCalendarTool> {
  private final Provider<Context> contextProvider;

  public OpenCalendarTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OpenCalendarTool get() {
    return newInstance(contextProvider.get());
  }

  public static OpenCalendarTool_Factory create(Provider<Context> contextProvider) {
    return new OpenCalendarTool_Factory(contextProvider);
  }

  public static OpenCalendarTool newInstance(Context context) {
    return new OpenCalendarTool(context);
  }
}
