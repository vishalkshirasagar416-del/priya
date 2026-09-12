package com.priya.app.presentation.viewmodel;

import com.priya.app.domain.repository.PriyaRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class PriyaViewModel_Factory implements Factory<PriyaViewModel> {
  private final Provider<PriyaRepository> repositoryProvider;

  public PriyaViewModel_Factory(Provider<PriyaRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public PriyaViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static PriyaViewModel_Factory create(Provider<PriyaRepository> repositoryProvider) {
    return new PriyaViewModel_Factory(repositoryProvider);
  }

  public static PriyaViewModel newInstance(PriyaRepository repository) {
    return new PriyaViewModel(repository);
  }
}
