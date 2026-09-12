package com.priya.app.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0019R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lcom/priya/app/presentation/viewmodel/VoiceSettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "preferences", "Lcom/priya/app/data/voice/VoicePreferences;", "kokoroModelManager", "Lcom/priya/app/data/voice/KokoroModelManager;", "(Lcom/priya/app/data/voice/VoicePreferences;Lcom/priya/app/data/voice/KokoroModelManager;)V", "_settings", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/priya/app/domain/voice/VoiceSettings;", "modelState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/priya/app/data/voice/KokoroModelState;", "getModelState", "()Lkotlinx/coroutines/flow/StateFlow;", "settings", "getSettings", "ensureKokoroModel", "", "setKokoroVoice", "value", "", "setVoiceEngine", "Lcom/priya/app/domain/voice/VoiceEnginePreference;", "setVoicePitch", "", "setVoiceSpeed", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class VoiceSettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.VoicePreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.KokoroModelManager kokoroModelManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.priya.app.domain.voice.VoiceSettings> _settings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.priya.app.domain.voice.VoiceSettings> settings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.priya.app.data.voice.KokoroModelState> modelState = null;
    
    @javax.inject.Inject()
    public VoiceSettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.VoicePreferences preferences, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.KokoroModelManager kokoroModelManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.priya.app.domain.voice.VoiceSettings> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.priya.app.data.voice.KokoroModelState> getModelState() {
        return null;
    }
    
    public final void setVoiceEngine(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.voice.VoiceEnginePreference value) {
    }
    
    public final void setKokoroVoice(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setVoiceSpeed(float value) {
    }
    
    public final void setVoicePitch(float value) {
    }
    
    public final void ensureKokoroModel() {
    }
}