package org.d3if0043.monefysafe.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object Second: Screen("inputScreen")
}