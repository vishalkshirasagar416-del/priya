package com.priya.app.data.tools;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0004R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0014"}, d2 = {"Lcom/priya/app/data/tools/BaseTool;", "Lcom/priya/app/domain/tools/AssistantTool;", "name", "", "description", "parameters", "", "Lcom/priya/app/domain/tools/ToolParameter;", "context", "Landroid/content/Context;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Landroid/content/Context;)V", "getDescription", "()Ljava/lang/String;", "getName", "getParameters", "()Ljava/util/List;", "openIntent", "Lcom/priya/app/domain/tools/ToolResult;", "intent", "Landroid/content/Intent;", "app_debug"})
public abstract class BaseTool implements com.priya.app.domain.tools.AssistantTool {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.priya.app.domain.tools.ToolParameter> parameters = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public BaseTool(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.util.List<com.priya.app.domain.tools.ToolParameter> parameters, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getDescription() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.priya.app.domain.tools.ToolParameter> getParameters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.priya.app.domain.tools.ToolResult openIntent(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
}