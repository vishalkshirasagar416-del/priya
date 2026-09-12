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
public final class GetDeviceInfoTool_Factory implements Factory<GetDeviceInfoTool> {
  private final Provider<Context> contextProvider;

  public GetDeviceInfoTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public GetDeviceInfoTool get() {
    return newInstance(contextProvider.get());
  }

  public static GetDeviceInfoTool_Factory create(Provider<Context> contextProvider) {
    return new GetDeviceInfoTool_Factory(contextProvider);
  }

  public static GetDeviceInfoTool newInstance(Context context) {
    return new GetDeviceInfoTool(context);
  }
}
