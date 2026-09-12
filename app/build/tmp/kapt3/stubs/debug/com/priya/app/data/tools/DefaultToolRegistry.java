package com.priya.app.data.tools;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001a\b\u0007\u0012\u0011\u0010\u0002\u001a\r\u0012\t\u0012\u00070\u0004\u00a2\u0006\u0002\b\u00050\u0003\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0019\u0010\u0002\u001a\r\u0012\t\u0012\u00070\u0004\u00a2\u0006\u0002\b\u00050\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0010"}, d2 = {"Lcom/priya/app/data/tools/DefaultToolRegistry;", "Lcom/priya/app/domain/tools/ToolRegistry;", "registeredTools", "", "Lcom/priya/app/domain/tools/AssistantTool;", "Lkotlin/jvm/JvmSuppressWildcards;", "(Ljava/util/Set;)V", "tools", "", "getTools", "()Ljava/util/List;", "getTool", "name", "", "hasTool", "", "app_debug"})
public final class DefaultToolRegistry implements com.priya.app.domain.tools.ToolRegistry {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<com.priya.app.domain.tools.AssistantTool> registeredTools = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.priya.app.domain.tools.AssistantTool> tools = null;
    
    @javax.inject.Inject()
    public DefaultToolRegistry(@org.jetbrains.annotations.NotNull()
    java.util.Set<com.priya.app.domain.tools.AssistantTool> registeredTools) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.priya.app.domain.tools.AssistantTool> getTools() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public com.priya.app.domain.tools.AssistantTool getTool(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return null;
    }
    
    @java.lang.Override()
    public boolean hasTool(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return false;
    }
}