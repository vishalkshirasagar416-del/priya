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
public final class GetStorageStatusTool_Factory implements Factory<GetStorageStatusTool> {
  private final Provider<Context> contextProvider;

  public GetStorageStatusTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public GetStorageStatusTool get() {
    return newInstance(contextProvider.get());
  }

  public static GetStorageStatusTool_Factory create(Provider<Context> contextProvider) {
    return new GetStorageStatusTool_Factory(contextProvider);
  }

  public static GetStorageStatusTool newInstance(Context context) {
    return new GetStorageStatusTool(context);
  }
}
