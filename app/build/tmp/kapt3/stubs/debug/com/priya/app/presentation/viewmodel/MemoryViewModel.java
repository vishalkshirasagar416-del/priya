package com.priya.app.presentation.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/priya/app/presentation/viewmodel/MemoryViewModel;", "Landroidx/lifecycle/ViewModel;", "memoryRepository", "Lcom/priya/app/domain/repository/LocalMemoryRepository;", "(Lcom/priya/app/domain/repository/LocalMemoryRepository;)V", "memories", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/priya/app/domain/model/MemoryRecord;", "getMemories", "()Lkotlinx/coroutines/flow/StateFlow;", "clearAll", "", "deleteMemory", "id", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MemoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.domain.repository.LocalMemoryRepository memoryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.priya.app.domain.model.MemoryRecord>> memories = null;
    
    @javax.inject.Inject()
    public MemoryViewModel(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.repository.LocalMemoryRepository memoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.priya.app.domain.model.MemoryRecord>> getMemories() {
        return null;
    }
    
    public final void deleteMemory(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    public final void clearAll() {
    }
}