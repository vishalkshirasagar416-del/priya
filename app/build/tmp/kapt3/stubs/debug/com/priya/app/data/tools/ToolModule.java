package com.priya.app.data.tools;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\'J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\nH\'J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000eH\'J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0010H\'J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0012H\'J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0014H\'J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0016H\'J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0018H\'J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001aH\'J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001cH\'J\u0010\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001eH\'J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020 H\'J\u0010\u0010!\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\"H\'J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020$H\'J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020&H\'J\u0010\u0010\'\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020(H\'J\u0010\u0010)\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020*H\'J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020,H\'J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020.H\'\u00a8\u0006/"}, d2 = {"Lcom/priya/app/data/tools/ToolModule;", "", "()V", "bindCancelAlarmTool", "Lcom/priya/app/domain/tools/AssistantTool;", "tool", "Lcom/priya/app/data/tools/CancelAlarmTool;", "bindCreateReminderTool", "Lcom/priya/app/data/tools/CreateReminderTool;", "bindCreateTimerTool", "Lcom/priya/app/data/tools/CreateTimerTool;", "bindDeleteReminderTool", "Lcom/priya/app/data/tools/DeleteReminderTool;", "bindGetBatteryStatusTool", "Lcom/priya/app/data/tools/GetBatteryStatusTool;", "bindGetDateTool", "Lcom/priya/app/data/tools/GetDateTool;", "bindGetDeviceInfoTool", "Lcom/priya/app/data/tools/GetDeviceInfoTool;", "bindGetLocationTool", "Lcom/priya/app/data/tools/GetLocationTool;", "bindGetNetworkStatusTool", "Lcom/priya/app/data/tools/GetNetworkStatusTool;", "bindGetStorageStatusTool", "Lcom/priya/app/data/tools/GetStorageStatusTool;", "bindGetTimeTool", "Lcom/priya/app/data/tools/GetTimeTool;", "bindMakeCallTool", "Lcom/priya/app/data/tools/MakeCallTool;", "bindOpenAppTool", "Lcom/priya/app/data/tools/OpenAppTool;", "bindOpenBrowserTool", "Lcom/priya/app/data/tools/OpenBrowserTool;", "bindOpenCalendarTool", "Lcom/priya/app/data/tools/OpenCalendarTool;", "bindOpenDialerTool", "Lcom/priya/app/data/tools/OpenDialerTool;", "bindOpenMapsTool", "Lcom/priya/app/data/tools/OpenMapsTool;", "bindOpenSettingsTool", "Lcom/priya/app/data/tools/OpenSettingsTool;", "bindOpenWhatsAppTool", "Lcom/priya/app/data/tools/OpenWhatsAppTool;", "bindSearchWebTool", "Lcom/priya/app/data/tools/SearchWebTool;", "bindSetAlarmTool", "Lcom/priya/app/data/tools/SetAlarmTool;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class ToolModule {
    
    public ToolModule() {
        super();
    }
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindOpenAppTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.OpenAppTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindOpenSettingsTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.OpenSettingsTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindOpenDialerTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.OpenDialerTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindMakeCallTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.MakeCallTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindOpenWhatsAppTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.OpenWhatsAppTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindOpenBrowserTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.OpenBrowserTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindSearchWebTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.SearchWebTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindGetTimeTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.GetTimeTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindGetDateTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.GetDateTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindGetBatteryStatusTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.GetBatteryStatusTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindGetStorageStatusTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.GetStorageStatusTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindGetNetworkStatusTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.GetNetworkStatusTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindGetDeviceInfoTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.GetDeviceInfoTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindSetAlarmTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.SetAlarmTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindCancelAlarmTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.CancelAlarmTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindCreateTimerTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.CreateTimerTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindCreateReminderTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.CreateReminderTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindDeleteReminderTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.DeleteReminderTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindGetLocationTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.GetLocationTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindOpenMapsTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.OpenMapsTool tool);
    
    @dagger.Binds()
    @dagger.multibindings.IntoSet()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.tools.AssistantTool bindOpenCalendarTool(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.tools.OpenCalendarTool tool);
}