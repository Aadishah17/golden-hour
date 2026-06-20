package com.goldenhour.utils;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/goldenhour/utils/SoundEffects;", "", "()V", "toneGenerator", "Landroid/media/ToneGenerator;", "playAlert", "", "playBeep", "playSuccess", "app_debug"})
public final class SoundEffects {
    @org.jetbrains.annotations.Nullable()
    private android.media.ToneGenerator toneGenerator;
    
    @javax.inject.Inject()
    public SoundEffects() {
        super();
    }
    
    /**
     * Play a short, high-frequency navigation/checklist beep.
     */
    public final void playBeep() {
    }
    
    /**
     * Play a sharp alarm alert pulse.
     */
    public final void playAlert() {
    }
    
    /**
     * Play a success/arrival high melody sequence.
     */
    public final void playSuccess() {
    }
}