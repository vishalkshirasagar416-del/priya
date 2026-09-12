package com.priya.app.data.tools

import com.priya.app.domain.tools.AssistantTool
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ToolModule {

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindOpenAppTool(tool: OpenAppTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindOpenSettingsTool(tool: OpenSettingsTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindOpenDialerTool(tool: OpenDialerTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindMakeCallTool(tool: MakeCallTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindOpenWhatsAppTool(tool: OpenWhatsAppTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindOpenBrowserTool(tool: OpenBrowserTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindSearchWebTool(tool: SearchWebTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindGetTimeTool(tool: GetTimeTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindGetDateTool(tool: GetDateTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindGetBatteryStatusTool(tool: GetBatteryStatusTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindGetStorageStatusTool(tool: GetStorageStatusTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindGetNetworkStatusTool(tool: GetNetworkStatusTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindGetDeviceInfoTool(tool: GetDeviceInfoTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindSetAlarmTool(tool: SetAlarmTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindCancelAlarmTool(tool: CancelAlarmTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindCreateTimerTool(tool: CreateTimerTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindCreateReminderTool(tool: CreateReminderTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindDeleteReminderTool(tool: DeleteReminderTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindGetLocationTool(tool: GetLocationTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindOpenMapsTool(tool: OpenMapsTool): AssistantTool

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindOpenCalendarTool(tool: OpenCalendarTool): AssistantTool
}
