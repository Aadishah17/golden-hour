package com.goldenhour.model;

/**
 * Data model representing the user's triage input details.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J<\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020\nH\u00d6\u0001J\t\u0010!\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0005\u0010\u000eR\u0011\u0010\u0012\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0002\u0010\u000eR\u0011\u0010\u0014\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016\u00a8\u0006\""}, d2 = {"Lcom/goldenhour/model/TriageData;", "", "isConscious", "", "heavyBleeding", "isBreathing", "voiceReport", "", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)V", "glasgowComaScale", "", "getGlasgowComaScale", "()I", "getHeavyBleeding", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "injurySeverityScore", "getInjurySeverityScore", "isComplete", "()Z", "severityLevel", "getSeverityLevel", "()Ljava/lang/String;", "getVoiceReport", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/goldenhour/model/TriageData;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class TriageData {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean isConscious = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean heavyBleeding = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean isBreathing = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String voiceReport = null;
    
    public TriageData(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean isConscious, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean heavyBleeding, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isBreathing, @org.jetbrains.annotations.NotNull()
    java.lang.String voiceReport) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isConscious() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean getHeavyBleeding() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isBreathing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVoiceReport() {
        return null;
    }
    
    public final boolean isComplete() {
        return false;
    }
    
    public final int getGlasgowComaScale() {
        return 0;
    }
    
    public final int getInjurySeverityScore() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSeverityLevel() {
        return null;
    }
    
    public TriageData() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.goldenhour.model.TriageData copy(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean isConscious, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean heavyBleeding, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isBreathing, @org.jetbrains.annotations.NotNull()
    java.lang.String voiceReport) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}