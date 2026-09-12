package com.priya.app.presentation.viewmodel;

import com.priya.app.domain.repository.LocalMemoryRepository;
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
public final class MemoryViewModel_Factory implements Factory<MemoryViewModel> {
  private final Provider<LocalMemoryRepository> memoryRepositoryProvider;

  public MemoryViewModel_Factory(Provider<LocalMemoryRepository> memoryRepositoryProvider) {
    this.memoryRepositoryProvider = memoryRepositoryProvider;
  }

  @Override
  public MemoryViewModel get() {
    return newInstance(memoryRepositoryProvider.get());
  }

  public static MemoryViewModel_Factory create(
      Provider<LocalMemoryRepository> memoryRepositoryProvider) {
    return new MemoryViewModel_Factory(memoryRepositoryProvider);
  }

  public static MemoryViewModel newInstance(LocalMemoryRepository memoryRepository) {
    return new MemoryViewModel(memoryRepository);
  }
}
