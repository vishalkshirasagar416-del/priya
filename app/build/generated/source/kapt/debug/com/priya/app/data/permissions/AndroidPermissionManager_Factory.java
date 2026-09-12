package com.priya.app.data.permissions;

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
public final class AndroidPermissionManager_Factory implements Factory<AndroidPermissionManager> {
  private final Provider<Context> contextProvider;

  public AndroidPermissionManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AndroidPermissionManager get() {
    return newInstance(contextProvider.get());
  }

  public static AndroidPermissionManager_Factory create(Provider<Context> contextProvider) {
    return new AndroidPermissionManager_Factory(contextProvider);
  }

  public static AndroidPermissionManager newInstance(Context context) {
    return new AndroidPermissionManager(context);
  }
}
