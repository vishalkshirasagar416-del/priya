package com.priya.app.di;

import com.priya.app.services.PermissionService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AppModule_Companion_ProvidePermissionServiceFactory implements Factory<PermissionService> {
  @Override
  public PermissionService get() {
    return providePermissionService();
  }

  public static AppModule_Companion_ProvidePermissionServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PermissionService providePermissionService() {
    return Preconditions.checkNotNullFromProvides(AppModule.Companion.providePermissionService());
  }

  private static final class InstanceHolder {
    private static final AppModule_Companion_ProvidePermissionServiceFactory INSTANCE = new AppModule_Companion_ProvidePermissionServiceFactory();
  }
}
