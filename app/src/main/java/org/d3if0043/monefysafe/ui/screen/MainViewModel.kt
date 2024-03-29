package org.d3if0043.monefysafe.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0043.monefysafe.model.Transaksi

class MainViewModel : ViewModel() {
    val data = getDataTransaksi()
    private fun getDataTransaksi() : List<Transaksi>{
    val data = mutableListOf<Transaksi>()
        for (i in 1 until 10){
            data.add(
                Transaksi(
                    i.toInt(),
                    "Ini ke-$i",
                    "Pemasukan",
                    "$i/03/2024"
                )
            )
        }
        return data
    }
}