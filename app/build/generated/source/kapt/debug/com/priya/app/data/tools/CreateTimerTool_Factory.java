package com.priya.app.data.tools;

import android.content.Context;
import com.priya.app.domain.scheduler.ScheduledTaskScheduler;
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
public final class CreateTimerTool_Factory implements Factory<CreateTimerTool> {
  private final Provider<Context> contextProvider;

  private final Provider<ScheduledTaskScheduler> schedulerProvider;

  public CreateTimerTool_Factory(Provider<Context> contextProvider,
      Provider<ScheduledTaskScheduler> schedulerProvider) {
    this.contextProvider = contextProvider;
    this.schedulerProvider = schedulerProvider;
  }

  @Override
  public CreateTimerTool get() {
    return newInstance(contextProvider.get(), schedulerProvider.get());
  }

  public static CreateTimerTool_Factory create(Provider<Context> contextProvider,
      Provider<ScheduledTaskScheduler> schedulerProvider) {
    return new CreateTimerTool_Factory(contextProvider, schedulerProvider);
  }

  public static CreateTimerTool newInstance(Context context, ScheduledTaskScheduler scheduler) {
    return new CreateTimerTool(context, scheduler);
  }
}
