package com.priya.app.data.repository;

import android.content.Context;
import com.priya.app.data.local.PriyaDao;
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
public final class LocalMemoryRepositoryImpl_Factory implements Factory<LocalMemoryRepositoryImpl> {
  private final Provider<PriyaDao> daoProvider;

  private final Provider<Context> contextProvider;

  public LocalMemoryRepositoryImpl_Factory(Provider<PriyaDao> daoProvider,
      Provider<Context> contextProvider) {
    this.daoProvider = daoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public LocalMemoryRepositoryImpl get() {
    return newInstance(daoProvider.get(), contextProvider.get());
  }

  public static LocalMemoryRepositoryImpl_Factory create(Provider<PriyaDao> daoProvider,
      Provider<Context> contextProvider) {
    return new LocalMemoryRepositoryImpl_Factory(daoProvider, contextProvider);
  }

  public static LocalMemoryRepositoryImpl newInstance(PriyaDao dao, Context context) {
    return new LocalMemoryRepositoryImpl(dao, context);
  }
}
