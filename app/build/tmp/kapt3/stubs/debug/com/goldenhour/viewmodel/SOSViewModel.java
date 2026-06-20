package com.goldenhour.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0015J\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\rR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/goldenhour/viewmodel/SOSViewModel;", "Landroidx/lifecycle/ViewModel;", "emergencyRepository", "Lcom/goldenhour/repository/EmergencyRepository;", "languageRepository", "Lcom/goldenhour/repository/LanguageRepository;", "soundEffects", "Lcom/goldenhour/utils/SoundEffects;", "(Lcom/goldenhour/repository/EmergencyRepository;Lcom/goldenhour/repository/LanguageRepository;Lcom/goldenhour/utils/SoundEffects;)V", "accidentLocation", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/goldenhour/model/AccidentLocation;", "getAccidentLocation", "()Lkotlinx/coroutines/flow/StateFlow;", "nearestHospital", "Lcom/goldenhour/model/HospitalInfo;", "getNearestHospital", "selectedLanguage", "", "getSelectedLanguage", "selectedLocationIndex", "", "getSelectedLocationIndex", "selectedVictimRange", "getSelectedVictimRange", "playSOSAlert", "", "selectLocation", "index", "selectVictimRange", "range", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SOSViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.repository.EmergencyRepository emergencyRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.repository.LanguageRepository languageRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.utils.SoundEffects soundEffects = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedLanguage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> selectedLocationIndex = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.AccidentLocation> accidentLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.HospitalInfo> nearestHospital = null;
    
    /**
     * Map the repository's integer victim count to String ranges.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedVictimRange = null;
    
    @javax.inject.Inject()
    public SOSViewModel(@org.jetbrains.annotations.NotNull()
    com.goldenhour.repository.EmergencyRepository emergencyRepository, @org.jetbrains.annotations.NotNull()
    com.goldenhour.repository.LanguageRepository languageRepository, @org.jetbrains.annotations.NotNull()
    com.goldenhour.utils.SoundEffects soundEffects) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getSelectedLocationIndex() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.AccidentLocation> getAccidentLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.HospitalInfo> getNearestHospital() {
        return null;
    }
    
    public final void selectLocation(int index) {
    }
    
    public final void playSOSAlert() {
    }
    
    /**
     * Map the repository's integer victim count to String ranges.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedVictimRange() {
        return null;
    }
    
    /**
     * Save the user's selected victim range to the shared repository.
     */
    public final void selectVictimRange(@org.jetbrains.annotations.NotNull()
    java.lang.String range) {
    }
}