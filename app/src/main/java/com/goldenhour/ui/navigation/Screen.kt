package com.goldenhour.ui.navigation

sealed class Screen(val route: String) {
    data object Language : Screen("language")
    data object GoodSamaritan : Screen("good-samaritan")
    data object Sos : Screen("sos")
    data object Triage : Screen("triage")
    data object Alerting : Screen("alerting")
    data object FirstAid : Screen("first-aid")
    data object Dashboard : Screen("dashboard")
}
