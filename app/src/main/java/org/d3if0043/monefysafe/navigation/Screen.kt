package org.d3if0043.monefysafe.navigation

import org.d3if0043.monefysafe.ui.screen.KEY_ID_TRANSAKSI

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object Second: Screen("listScreen")
    data object FormUbah: Screen("mainScreen/{$KEY_ID_TRANSAKSI}") {
        fun withId(id:Long) = "mainScreen/$id"
    }
}