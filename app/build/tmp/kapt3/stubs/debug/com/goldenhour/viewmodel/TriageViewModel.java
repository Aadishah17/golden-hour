package com.goldenhour.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\rJ\u0006\u0010!\u001a\u00020\u001fJ\u0006\u0010\"\u001a\u00020\u001fJ\u0006\u0010#\u001a\u00020\u001fJ\u000e\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u0017R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013\u00a8\u0006&"}, d2 = {"Lcom/goldenhour/viewmodel/TriageViewModel;", "Landroidx/lifecycle/ViewModel;", "emergencyRepository", "Lcom/goldenhour/repository/EmergencyRepository;", "languageRepository", "Lcom/goldenhour/repository/LanguageRepository;", "soundEffects", "Lcom/goldenhour/utils/SoundEffects;", "(Lcom/goldenhour/repository/EmergencyRepository;Lcom/goldenhour/repository/LanguageRepository;Lcom/goldenhour/utils/SoundEffects;)V", "_currentQuestionIndex", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isRecording", "", "_triageState", "Lcom/goldenhour/model/TriageData;", "currentQuestionIndex", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentQuestionIndex", "()Lkotlinx/coroutines/flow/StateFlow;", "isRecording", "questions", "", "", "getQuestions", "()Ljava/util/List;", "selectedLanguage", "getSelectedLanguage", "triageState", "getTriageState", "answerQuestion", "", "answer", "goBackQuestion", "resetTriage", "toggleRecording", "updateVoiceReport", "text", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TriageViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.repository.EmergencyRepository emergencyRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.repository.LanguageRepository languageRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.goldenhour.utils.SoundEffects soundEffects = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedLanguage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _currentQuestionIndex = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> currentQuestionIndex = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isRecording = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRecording = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.goldenhour.model.TriageData> _triageState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.TriageData> triageState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> questions = null;
    
    @javax.inject.Inject()
    public TriageViewModel(@org.jetbrains.annotations.NotNull()
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
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getCurrentQuestionIndex() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRecording() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.goldenhour.model.TriageData> getTriageState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getQuestions() {
        return null;
    }
    
    /**
     * Records the user's answers to the active question (YES/NO).
     */
    public final void answerQuestion(boolean answer) {
    }
    
    /**
     * Navigates back one step in the questions.
     */
    public final void goBackQuestion() {
    }
    
    /**
     * Directly update text area.
     */
    public final void updateVoiceReport(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    /**
     * Start/Stop Voice reporting simulation.
     */
    public final void toggleRecording() {
    }
    
    /**
     * Reset local states for a new emergency session.
     */
    public final void resetTriage() {
    }
}