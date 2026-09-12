package com.priya.app.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0012"}, d2 = {"Lcom/priya/app/presentation/viewmodel/PriyaViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/priya/app/domain/repository/PriyaRepository;", "(Lcom/priya/app/domain/repository/PriyaRepository;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/priya/app/presentation/viewmodel/PriyaUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addAssistantMessage", "", "text", "", "addUserMessage", "updateStatus", "status", "Lcom/priya/app/core/AssistantStatus;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PriyaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.domain.repository.PriyaRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.priya.app.presentation.viewmodel.PriyaUiState> uiState = null;
    
    @javax.inject.Inject()
    public PriyaViewModel(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.repository.PriyaRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.priya.app.presentation.viewmodel.PriyaUiState> getUiState() {
        return null;
    }
    
    public final void updateStatus(@org.jetbrains.annotations.NotNull()
    com.priya.app.core.AssistantStatus status) {
    }
    
    public final void addUserMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void addAssistantMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
}