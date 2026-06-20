package com.goldenhour.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020#R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00170\r\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000f\u00a8\u0006%"}, d2 = {"Lcom/goldenhour/viewmodel/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "emergencyRepository", "Lcom/goldenhour/repository/EmergencyRepository;", "languageRepository", "Lcom/goldenhour/repository/LanguageRepository;", "soundEffects", "Lcom/goldenhour/utils/SoundEffects;", "(Lcom/goldenhour/repository/EmergencyRepository;Lcom/goldenhour/repository/LanguageRepository;Lcom/goldenhour/utils/SoundEffects;)V", "_ambulanceState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/goldenhour/model/AmbulanceState;", "ambulanceState", "Lkotlinx/coroutines/flow/StateFlow;", "getAmbulanceState", "()Lkotlinx/coroutines/flow/StateFlow;", "nearestHospital", "Lcom/goldenhour/model/HospitalInfo;", "getNearestHospital", "selectedLanguage", "", "getSelectedLanguage", "selectedLocationIndex", "", "getSelectedLocationIndex", "traumaTeamChecklist", "", "getTraumaTeamChecklist", "()Ljava/util/List;", "triageData", "Lcom/goldenhour/model/TriageData;", "getTriageData", "victimCount", "getVictimCount", "resetSession", "", "startAmbulanceSimulation", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.repository.EmergencyRepository emergencyRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.repository.LanguageRepository languageRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.utils.SoundEffects soundEffects = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedLanguage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.TriageData> triageData = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> victimCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.HospitalInfo> nearestHospital = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> selectedLocationIndex = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.goldenhour.model.AmbulanceState> _ambulanceState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.AmbulanceState> ambulanceState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> traumaTeamChecklist = null;
    
    @javax.inject.Inject()
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
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
    public final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.TriageData> getTriageData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getVictimCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.HospitalInfo> getNearestHospital() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getSelectedLocationIndex() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.AmbulanceState> getAmbulanceState() {
        return null;
    }
    
    /**
     * Actively listens to the repository's simulated ambulance telemetry stream.
     */
    public final void startAmbulanceSimulation() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTraumaTeamChecklist() {
        return null;
    }
    
    public final void resetSession() {
    }
}