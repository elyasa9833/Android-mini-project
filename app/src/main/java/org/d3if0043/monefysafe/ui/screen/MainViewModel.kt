package org.d3if0043.monefysafe.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if0043.monefysafe.database.TransaksiDao
import org.d3if0043.monefysafe.model.Transaksi

class MainViewModel(dao: TransaksiDao) : ViewModel() {
    val data: StateFlow<List<Transaksi>> = dao.getTransaksi().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}
