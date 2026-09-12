package com.priya.app.data.scheduler;

import android.content.Context;
import com.priya.app.data.local.PriyaDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class PriyaSchedulerImpl_Factory implements Factory<PriyaSchedulerImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<PriyaDao> priyaDaoProvider;

  public PriyaSchedulerImpl_Factory(Provider<Context> contextProvider,
      Provider<PriyaDao> priyaDaoProvider) {
    this.contextProvider = contextProvider;
    this.priyaDaoProvider = priyaDaoProvider;
  }

  @Override
  public PriyaSchedulerImpl get() {
    return newInstance(contextProvider.get(), priyaDaoProvider.get());
  }

  public static PriyaSchedulerImpl_Factory create(Provider<Context> contextProvider,
      Provider<PriyaDao> priyaDaoProvider) {
    return new PriyaSchedulerImpl_Factory(contextProvider, priyaDaoProvider);
  }

  public static PriyaSchedulerImpl newInstance(Context context, PriyaDao priyaDao) {
    return new PriyaSchedulerImpl(context, priyaDao);
  }
}
