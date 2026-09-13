package com.priya.app.di

import android.content.Context
import com.priya.app.data.ai.GeminiProvider
import com.priya.app.data.ai.OpenRouterProvider
import com.priya.app.data.ai.backend.BackendApi
import com.priya.app.data.ai.backend.DefaultBackendApi
import com.priya.app.data.local.PriyaDao
import com.priya.app.data.local.PriyaDatabase
import com.priya.app.data.permissions.AndroidPermissionManager
import com.priya.app.data.repository.AIRepositoryImpl
import com.priya.app.data.repository.SecureAIConfigRepository
import com.priya.app.data.scheduler.PriyaSchedulerImpl
import com.priya.app.data.repository.DefaultPriyaRepository
import com.priya.app.data.repository.InMemoryConversationRepository
import com.priya.app.data.repository.LocalMemoryRepositoryImpl
import com.priya.app.data.router.DefaultLLMToolGenerator
import com.priya.app.data.tools.DefaultToolRegistry
import com.priya.app.data.voice.AndroidSpeechToTextProvider
import com.priya.app.data.voice.DefaultWakeWordDetector
import com.priya.app.data.voice.ElevenLabsTTSProvider
import com.priya.app.data.voice.KokoroModelManager
import com.priya.app.data.voice.KokoroTTSProvider
import com.priya.app.data.voice.LocalAndroidTTSProvider
import com.priya.app.data.voice.PriyaVoiceManager
import com.priya.app.domain.ai.AIProvider
import com.priya.app.domain.permissions.PermissionManager
import com.priya.app.domain.repository.AIRepository
import com.priya.app.domain.repository.AIConfigRepository
import com.priya.app.domain.repository.ConversationRepository
import com.priya.app.domain.scheduler.ScheduledTaskScheduler
import com.priya.app.domain.repository.LocalMemoryRepository
import com.priya.app.domain.repository.PriyaRepository
import com.priya.app.domain.router.LLMToolGenerator
import com.priya.app.domain.tools.ToolRegistry
import com.priya.app.domain.voice.SpeechToText
import com.priya.app.domain.voice.WakeWordDetector
import com.priya.app.services.AudioService
import com.priya.app.services.DefaultAudioService
import com.priya.app.services.DefaultPermissionService
import com.priya.app.services.PermissionService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindPriyaRepository(repository: DefaultPriyaRepository): PriyaRepository

    @Binds
    @Singleton
    abstract fun bindConversationRepository(repository: InMemoryConversationRepository): ConversationRepository

    @Binds
    @Singleton
    @Named("gemini")
    abstract fun bindGeminiProvider(provider: GeminiProvider): AIProvider

    @Binds
    @Singleton
    @Named("openRouter")
    abstract fun bindOpenRouterProvider(provider: OpenRouterProvider): AIProvider

    @Binds
    @Singleton
    abstract fun bindAIRepository(repository: AIRepositoryImpl): AIRepository

    @Binds
    @Singleton
    abstract fun bindAIConfigRepository(repository: SecureAIConfigRepository): AIConfigRepository

    @Binds
    @Singleton
    abstract fun bindPermissionManager(manager: AndroidPermissionManager): PermissionManager

    @Binds
    @Singleton
    abstract fun bindBackendApi(api: DefaultBackendApi): BackendApi

    @Binds
    @Singleton
    abstract fun bindScheduledTaskScheduler(scheduler: PriyaSchedulerImpl): ScheduledTaskScheduler

    @Binds
    @Singleton
    abstract fun bindLocalMemoryRepository(repository: LocalMemoryRepositoryImpl): LocalMemoryRepository

    @Binds
    @Singleton
    abstract fun bindSpeechToText(provider: AndroidSpeechToTextProvider): SpeechToText

    @Binds
    @Singleton
    abstract fun bindWakeWordDetector(detector: DefaultWakeWordDetector): WakeWordDetector

    @Binds
    @Singleton
    abstract fun bindToolRegistry(registry: DefaultToolRegistry): ToolRegistry

    @Binds
    @Singleton
    abstract fun bindLLMToolGenerator(generator: DefaultLLMToolGenerator): LLMToolGenerator

    companion object {
        @Provides
        @Singleton
        fun provideAppContext(@ApplicationContext context: Context): Context = context

        @Provides
        @Singleton
        fun providePriyaDatabase(@ApplicationContext context: Context): PriyaDatabase =
            PriyaDatabase.getInstance(context)

        @Provides
        @Singleton
        fun providePriyaDao(database: PriyaDatabase): PriyaDao = database.priyaDao()

        @Provides
        @Singleton
        fun provideAudioService(service: DefaultAudioService): AudioService = service

        @Provides
        @Singleton
        fun providePermissionService(service: DefaultPermissionService): PermissionService = service

        @Provides
        @Singleton
        fun provideVoiceManager(
            elevenLabsTTSProvider: ElevenLabsTTSProvider,
            kokoroTTSProvider: KokoroTTSProvider,
            localAndroidTTSProvider: LocalAndroidTTSProvider,
            voicePreferences: com.priya.app.data.voice.VoicePreferences,
            kokoroModelManager: KokoroModelManager,
        ): PriyaVoiceManager = PriyaVoiceManager(
            elevenLabsTTSProvider,
            kokoroTTSProvider,
            localAndroidTTSProvider,
            voicePreferences,
            kokoroModelManager,
        )
    }
}
