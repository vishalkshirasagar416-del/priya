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
public final class SetAlarmTool_Factory implements Factory<SetAlarmTool> {
  private final Provider<Context> contextProvider;

  private final Provider<ScheduledTaskScheduler> schedulerProvider;

  public SetAlarmTool_Factory(Provider<Context> contextProvider,
      Provider<ScheduledTaskScheduler> schedulerProvider) {
    this.contextProvider = contextProvider;
    this.schedulerProvider = schedulerProvider;
  }

  @Override
  public SetAlarmTool get() {
    return newInstance(contextProvider.get(), schedulerProvider.get());
  }

  public static SetAlarmTool_Factory create(Provider<Context> contextProvider,
      Provider<ScheduledTaskScheduler> schedulerProvider) {
    return new SetAlarmTool_Factory(contextProvider, schedulerProvider);
  }

  public static SetAlarmTool newInstance(Context context, ScheduledTaskScheduler scheduler) {
    return new SetAlarmTool(context, scheduler);
  }
}
