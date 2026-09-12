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
public final class GetBatteryStatusTool_Factory implements Factory<GetBatteryStatusTool> {
  private final Provider<Context> contextProvider;

  public GetBatteryStatusTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public GetBatteryStatusTool get() {
    return newInstance(contextProvider.get());
  }

  public static GetBatteryStatusTool_Factory create(Provider<Context> contextProvider) {
    return new GetBatteryStatusTool_Factory(contextProvider);
  }

  public static GetBatteryStatusTool newInstance(Context context) {
    return new GetBatteryStatusTool(context);
  }
}
