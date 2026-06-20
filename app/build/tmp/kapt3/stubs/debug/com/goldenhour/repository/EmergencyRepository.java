package com.goldenhour.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0005J\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J\u0006\u0010\"\u001a\u00020\u0007J\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\tJ\u000e\u0010\'\u001a\u00020$2\u0006\u0010(\u001a\u00020\tJ\u000e\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0010\u00a8\u0006+"}, d2 = {"Lcom/goldenhour/repository/EmergencyRepository;", "", "()V", "_accidentLocation", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/goldenhour/model/AccidentLocation;", "_nearestHospital", "Lcom/goldenhour/model/HospitalInfo;", "_selectedLocationIndex", "", "_triageData", "Lcom/goldenhour/model/TriageData;", "_victimCount", "accidentLocation", "Lkotlinx/coroutines/flow/StateFlow;", "getAccidentLocation", "()Lkotlinx/coroutines/flow/StateFlow;", "hospitals", "", "getHospitals", "()Ljava/util/List;", "locations", "getLocations", "nearestHospital", "getNearestHospital", "selectedLocationIndex", "getSelectedLocationIndex", "triageData", "getTriageData", "victimCount", "getVictimCount", "getAmbulanceTrackingFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/goldenhour/model/AmbulanceState;", "getNearestTraumaCenter", "resetEmergencyState", "", "selectLocation", "index", "setVictimCount", "count", "updateTriageData", "data", "app_debug"})
public final class EmergencyRepository {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _victimCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> victimCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.goldenhour.model.TriageData> _triageData = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.TriageData> triageData = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.goldenhour.model.AccidentLocation> locations = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.goldenhour.model.HospitalInfo> hospitals = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _selectedLocationIndex = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> selectedLocationIndex = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.goldenhour.model.AccidentLocation> _accidentLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.AccidentLocation> accidentLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.goldenhour.model.HospitalInfo> _nearestHospital = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.HospitalInfo> nearestHospital = null;
    
    @javax.inject.Inject()
    public EmergencyRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getVictimCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.TriageData> getTriageData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.goldenhour.model.AccidentLocation> getLocations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.goldenhour.model.HospitalInfo> getHospitals() {
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
    
    /**
     * Return mock accident location details.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.goldenhour.model.AccidentLocation getAccidentLocation() {
        return null;
    }
    
    /**
     * Return nearby trauma center information.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.goldenhour.model.HospitalInfo getNearestTraumaCenter() {
        return null;
    }
    
    /**
     * Set the selected victim count (1, 2-5, 5+).
     */
    public final void setVictimCount(int count) {
    }
    
    /**
     * Update current triage responses.
     */
    public final void updateTriageData(@org.jetbrains.annotations.NotNull()
    com.goldenhour.model.TriageData data) {
    }
    
    /**
     * Resets internal states when restarting the app flow.
     */
    public final void resetEmergencyState() {
    }
    
    /**
     * Simulates the real-time movement of an ambulance from MGM hospital to the scene.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.goldenhour.model.AmbulanceState> getAmbulanceTrackingFlow() {
        return null;
    }
}