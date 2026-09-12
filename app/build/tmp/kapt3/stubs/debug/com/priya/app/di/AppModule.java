package com.priya.app.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\'J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0010H\'J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0012H\'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0015H\'J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\'J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\u001cH\'J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\'\u00a8\u0006\""}, d2 = {"Lcom/priya/app/di/AppModule;", "", "()V", "bindAIRepository", "Lcom/priya/app/domain/repository/AIRepository;", "repository", "Lcom/priya/app/data/repository/AIRepositoryImpl;", "bindConversationRepository", "Lcom/priya/app/domain/repository/ConversationRepository;", "Lcom/priya/app/data/repository/InMemoryConversationRepository;", "bindGeminiProvider", "Lcom/priya/app/domain/ai/AIProvider;", "provider", "Lcom/priya/app/data/ai/GeminiProvider;", "bindLocalMemoryRepository", "Lcom/priya/app/domain/repository/LocalMemoryRepository;", "Lcom/priya/app/data/repository/LocalMemoryRepositoryImpl;", "bindOpenRouterProvider", "Lcom/priya/app/data/ai/OpenRouterProvider;", "bindPriyaRepository", "Lcom/priya/app/domain/repository/PriyaRepository;", "Lcom/priya/app/data/repository/DefaultPriyaRepository;", "bindScheduledTaskScheduler", "Lcom/priya/app/domain/scheduler/ScheduledTaskScheduler;", "scheduler", "Lcom/priya/app/data/scheduler/PriyaSchedulerImpl;", "bindSpeechToText", "Lcom/priya/app/domain/voice/SpeechToText;", "Lcom/priya/app/data/voice/AndroidSpeechToTextProvider;", "bindWakeWordDetector", "Lcom/priya/app/domain/voice/WakeWordDetector;", "detector", "Lcom/priya/app/data/voice/DefaultWakeWordDetector;", "Companion", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.priya.app.di.AppModule.Companion Companion = null;
    
    public AppModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.repository.PriyaRepository bindPriyaRepository(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.repository.DefaultPriyaRepository repository);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.repository.ConversationRepository bindConversationRepository(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.repository.InMemoryConversationRepository repository);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @javax.inject.Named(value = "gemini")
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.ai.AIProvider bindGeminiProvider(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.ai.GeminiProvider provider);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @javax.inject.Named(value = "openRouter")
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.ai.AIProvider bindOpenRouterProvider(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.ai.OpenRouterProvider provider);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.repository.AIRepository bindAIRepository(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.repository.AIRepositoryImpl repository);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.scheduler.ScheduledTaskScheduler bindScheduledTaskScheduler(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.scheduler.PriyaSchedulerImpl scheduler);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.repository.LocalMemoryRepository bindLocalMemoryRepository(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.repository.LocalMemoryRepositoryImpl repository);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.voice.SpeechToText bindSpeechToText(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.AndroidSpeechToTextProvider provider);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.voice.WakeWordDetector bindWakeWordDetector(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.DefaultWakeWordDetector detector);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0012\u0010\u000e\u001a\u00020\r2\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007J0\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007\u00a8\u0006\u001b"}, d2 = {"Lcom/priya/app/di/AppModule$Companion;", "", "()V", "provideAppContext", "Landroid/content/Context;", "context", "provideAudioService", "Lcom/priya/app/services/AudioService;", "providePermissionService", "Lcom/priya/app/services/PermissionService;", "providePriyaDao", "Lcom/priya/app/data/local/PriyaDao;", "database", "Lcom/priya/app/data/local/PriyaDatabase;", "providePriyaDatabase", "provideVoiceManager", "Lcom/priya/app/data/voice/PriyaVoiceManager;", "elevenLabsTTSProvider", "Lcom/priya/app/data/voice/ElevenLabsTTSProvider;", "kokoroTTSProvider", "Lcom/priya/app/data/voice/KokoroTTSProvider;", "localAndroidTTSProvider", "Lcom/priya/app/data/voice/LocalAndroidTTSProvider;", "voicePreferences", "Lcom/priya/app/data/voice/VoicePreferences;", "kokoroModelManager", "Lcom/priya/app/data/voice/KokoroModelManager;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final android.content.Context provideAppContext(@dagger.hilt.android.qualifiers.ApplicationContext()
        @org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.priya.app.data.local.PriyaDatabase providePriyaDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
        @org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.priya.app.data.local.PriyaDao providePriyaDao(@org.jetbrains.annotations.NotNull()
        com.priya.app.data.local.PriyaDatabase database) {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.priya.app.services.AudioService provideAudioService() {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.priya.app.services.PermissionService providePermissionService() {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.priya.app.data.voice.PriyaVoiceManager provideVoiceManager(@org.jetbrains.annotations.NotNull()
        com.priya.app.data.voice.ElevenLabsTTSProvider elevenLabsTTSProvider, @org.jetbrains.annotations.NotNull()
        com.priya.app.data.voice.KokoroTTSProvider kokoroTTSProvider, @org.jetbrains.annotations.NotNull()
        com.priya.app.data.voice.LocalAndroidTTSProvider localAndroidTTSProvider, @org.jetbrains.annotations.NotNull()
        com.priya.app.data.voice.VoicePreferences voicePreferences, @org.jetbrains.annotations.NotNull()
        com.priya.app.data.voice.KokoroModelManager kokoroModelManager) {
            return null;
        }
    }
}