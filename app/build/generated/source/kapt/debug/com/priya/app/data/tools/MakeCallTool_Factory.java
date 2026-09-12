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
public final class MakeCallTool_Factory implements Factory<MakeCallTool> {
  private final Provider<Context> contextProvider;

  public MakeCallTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public MakeCallTool get() {
    return newInstance(contextProvider.get());
  }

  public static MakeCallTool_Factory create(Provider<Context> contextProvider) {
    return new MakeCallTool_Factory(contextProvider);
  }

  public static MakeCallTool newInstance(Context context) {
    return new MakeCallTool(context);
  }
}
