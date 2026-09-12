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
public final class GetLocationTool_Factory implements Factory<GetLocationTool> {
  private final Provider<Context> contextProvider;

  public GetLocationTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public GetLocationTool get() {
    return newInstance(contextProvider.get());
  }

  public static GetLocationTool_Factory create(Provider<Context> contextProvider) {
    return new GetLocationTool_Factory(contextProvider);
  }

  public static GetLocationTool newInstance(Context context) {
    return new GetLocationTool(context);
  }
}
