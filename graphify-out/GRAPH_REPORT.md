# Graph Report - C:\Users\shaha\OneDrive\Desktop\GoldenHour  (2026-06-19)

## Corpus Check
- Corpus is ~7,248 words - fits in a single context window. You may not need a graph.

## Summary
- 89 nodes · 91 edges · 18 communities (10 shown, 8 thin omitted)
- Extraction: 73% EXTRACTED · 27% INFERRED · 0% AMBIGUOUS · INFERRED: 25 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- [[_COMMUNITY_Core UI and Navigation Framework|Core UI and Navigation Framework]]
- [[_COMMUNITY_Emergency Models and Repositories|Emergency Models and Repositories]]
- [[_COMMUNITY_SOS and Samaritan Components|SOS and Samaritan Components]]
- [[_COMMUNITY_Screen Destination Definitions|Screen Destination Definitions]]
- [[_COMMUNITY_Dependency Injection and Configuration|Dependency Injection and Configuration]]
- [[_COMMUNITY_Patient Triage Business Logic|Patient Triage Business Logic]]
- [[_COMMUNITY_Hospital Dashboard Views|Hospital Dashboard Views]]
- [[_COMMUNITY_Dashboard State Management|Dashboard State Management]]
- [[_COMMUNITY_Alerting State Management|Alerting State Management]]
- [[_COMMUNITY_Samaritan State Management|Samaritan State Management]]
- [[_COMMUNITY_Language State Management|Language State Management]]
- [[_COMMUNITY_SOS State Management|SOS State Management]]
- [[_COMMUNITY_Application Entry Point|Application Entry Point]]

## God Nodes (most connected - your core abstractions)
1. `EmergencyRepository` - 8 edges
2. `GoldenHourNavGraph()` - 8 edges
3. `HospitalDashboardScreen()` - 6 edges
4. `TriageViewModel` - 6 edges
5. `GoodSamaritanScreen()` - 5 edges
6. `SOSScreen()` - 5 edges
7. `InfoCard()` - 4 edges
8. `AppModule` - 3 edges
9. `TriageData` - 3 edges
10. `LanguageRepository` - 3 edges

## Surprising Connections (you probably didn't know these)
- `HospitalDashboardScreen()` --calls--> `AmbulanceTracker()`  [INFERRED]
  app/src/main/java/com/goldenhour/ui/screens/HospitalDashboardScreen.kt → app/src/main/java/com/goldenhour/ui/components/CommonComponents.kt
- `GoodSamaritanScreen()` --calls--> `GradientButton()`  [INFERRED]
  app/src/main/java/com/goldenhour/ui/screens/GoodSamaritanScreen.kt → app/src/main/java/com/goldenhour/ui/components/CommonComponents.kt
- `SOSScreen()` --calls--> `PulseSOSButton()`  [INFERRED]
  app/src/main/java/com/goldenhour/ui/screens/SOSScreen.kt → app/src/main/java/com/goldenhour/ui/components/CommonComponents.kt
- `HospitalDashboardScreen()` --calls--> `InfoCard()`  [INFERRED]
  app/src/main/java/com/goldenhour/ui/screens/HospitalDashboardScreen.kt → app/src/main/java/com/goldenhour/ui/components/CommonComponents.kt
- `GoodSamaritanScreen()` --calls--> `FAQAccordion()`  [INFERRED]
  app/src/main/java/com/goldenhour/ui/screens/GoodSamaritanScreen.kt → app/src/main/java/com/goldenhour/ui/components/CommonComponents.kt

## Communities (18 total, 8 thin omitted)

### Community 0 - "Core UI and Navigation Framework"
Cohesion: 0.13
Nodes (9): AnimatedChecklist(), ProgressStepper(), MainActivity, GoldenHourNavGraph(), AlertingScreen(), LanguageOptionButton(), LanguageSelectionScreen(), QuickTriageScreen() (+1 more)

### Community 1 - "Emergency Models and Repositories"
Cohesion: 0.18
Nodes (5): AccidentLocation, AmbulanceState, HospitalInfo, TriageData, EmergencyRepository

### Community 2 - "SOS and Samaritan Components"
Cohesion: 0.25
Nodes (8): AmbulanceTracker(), FAQAccordion(), GradientButton(), InfoCard(), PulseSOSButton(), VictimSelector(), GoodSamaritanScreen(), SOSScreen()

### Community 3 - "Screen Destination Definitions"
Cohesion: 0.25
Nodes (7): Alerting, GoodSamaritan, HospitalDashboard, LanguageSelection, QuickTriage, Screen, SOS

### Community 6 - "Hospital Dashboard Views"
Cohesion: 0.83
Nodes (3): FutureAICard(), HospitalDashboardScreen(), TriageSummaryItem()

## Knowledge Gaps
- **10 isolated node(s):** `GoldenHourApp`, `Screen`, `LanguageSelection`, `GoodSamaritan`, `SOS` (+5 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **8 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `GoldenHourNavGraph()` connect `Core UI and Navigation Framework` to `SOS and Samaritan Components`, `Hospital Dashboard Views`?**
  _High betweenness centrality (0.074) - this node is a cross-community bridge._
- **Why does `EmergencyRepository` connect `Emergency Models and Repositories` to `Dependency Injection and Configuration`?**
  _High betweenness centrality (0.054) - this node is a cross-community bridge._
- **Are the 7 inferred relationships involving `GoldenHourNavGraph()` (e.g. with `.onCreate()` and `LanguageSelectionScreen()`) actually correct?**
  _`GoldenHourNavGraph()` has 7 INFERRED edges - model-reasoned connections that need verification._
- **Are the 3 inferred relationships involving `HospitalDashboardScreen()` (e.g. with `GoldenHourNavGraph()` and `AmbulanceTracker()`) actually correct?**
  _`HospitalDashboardScreen()` has 3 INFERRED edges - model-reasoned connections that need verification._
- **Are the 4 inferred relationships involving `GoodSamaritanScreen()` (e.g. with `GoldenHourNavGraph()` and `InfoCard()`) actually correct?**
  _`GoodSamaritanScreen()` has 4 INFERRED edges - model-reasoned connections that need verification._
- **What connects `GoldenHourApp`, `Screen`, `LanguageSelection` to the rest of the system?**
  _10 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Core UI and Navigation Framework` be split into smaller, more focused modules?**
  _Cohesion score 0.13 - nodes in this community are weakly interconnected._