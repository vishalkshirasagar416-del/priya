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
public final class GetDateTool_Factory implements Factory<GetDateTool> {
  private final Provider<Context> contextProvider;

  public GetDateTool_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public GetDateTool get() {
    return newInstance(contextProvider.get());
  }

  public static GetDateTool_Factory create(Provider<Context> contextProvider) {
    return new GetDateTool_Factory(contextProvider);
  }

  public static GetDateTool newInstance(Context context) {
    return new GetDateTool(context);
  }
}
