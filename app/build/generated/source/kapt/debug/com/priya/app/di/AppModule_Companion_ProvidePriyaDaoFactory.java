package com.priya.app.di;

import com.priya.app.data.local.PriyaDao;
import com.priya.app.data.local.PriyaDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_Companion_ProvidePriyaDaoFactory implements Factory<PriyaDao> {
  private final Provider<PriyaDatabase> databaseProvider;

  public AppModule_Companion_ProvidePriyaDaoFactory(Provider<PriyaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PriyaDao get() {
    return providePriyaDao(databaseProvider.get());
  }

  public static AppModule_Companion_ProvidePriyaDaoFactory create(
      Provider<PriyaDatabase> databaseProvider) {
    return new AppModule_Companion_ProvidePriyaDaoFactory(databaseProvider);
  }

  public static PriyaDao providePriyaDao(PriyaDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.Companion.providePriyaDao(database));
  }
}
