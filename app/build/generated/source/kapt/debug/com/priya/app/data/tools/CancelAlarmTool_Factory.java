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
public final class CancelAlarmTool_Factory implements Factory<CancelAlarmTool> {
  private final Provider<Context> contextProvider;

  private final Provider<ScheduledTaskScheduler> schedulerProvider;

  public CancelAlarmTool_Factory(Provider<Context> contextProvider,
      Provider<ScheduledTaskScheduler> schedulerProvider) {
    this.contextProvider = contextProvider;
    this.schedulerProvider = schedulerProvider;
  }

  @Override
  public CancelAlarmTool get() {
    return newInstance(contextProvider.get(), schedulerProvider.get());
  }

  public static CancelAlarmTool_Factory create(Provider<Context> contextProvider,
      Provider<ScheduledTaskScheduler> schedulerProvider) {
    return new CancelAlarmTool_Factory(contextProvider, schedulerProvider);
  }

  public static CancelAlarmTool newInstance(Context context, ScheduledTaskScheduler scheduler) {
    return new CancelAlarmTool(context, scheduler);
  }
}
