package com.priya.app.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DefaultPriyaRepository_Factory implements Factory<DefaultPriyaRepository> {
  @Override
  public DefaultPriyaRepository get() {
    return newInstance();
  }

  public static DefaultPriyaRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DefaultPriyaRepository newInstance() {
    return new DefaultPriyaRepository();
  }

  private static final class InstanceHolder {
    private static final DefaultPriyaRepository_Factory INSTANCE = new DefaultPriyaRepository_Factory();
  }
}
