package com.priya.app.domain.router;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J)\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00062\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000e\"\u00020\u0006H\u0002\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002\u00a8\u0006\u0012"}, d2 = {"Lcom/priya/app/domain/router/LocalIntentClassifier;", "", "()V", "classify", "Lcom/priya/app/domain/router/LocalIntentDecision;", "input", "", "extractAppName", "extractCallTarget", "text", "extractSearchQuery", "hasAny", "", "phrases", "", "(Ljava/lang/String;[Ljava/lang/String;)Z", "inferPackageName", "appName", "app_debug"})
public final class LocalIntentClassifier {
    
    public LocalIntentClassifier() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.priya.app.domain.router.LocalIntentDecision classify(@org.jetbrains.annotations.NotNull()
    java.lang.String input) {
        return null;
    }
    
    private final boolean hasAny(java.lang.String text, java.lang.String... phrases) {
        return false;
    }
    
    private final java.lang.String extractAppName(java.lang.String input) {
        return null;
    }
    
    private final java.lang.String inferPackageName(java.lang.String appName) {
        return null;
    }
    
    private final java.lang.String extractSearchQuery(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String extractCallTarget(java.lang.String text) {
        return null;
    }
}