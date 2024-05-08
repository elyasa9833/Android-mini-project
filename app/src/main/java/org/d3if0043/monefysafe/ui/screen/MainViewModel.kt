package org.d3if0043.monefysafe.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0043.monefysafe.model.Transaksi

class MainViewModel : ViewModel() {
    private val _data = MutableLiveData<List<Transaksi>>()
    val data: LiveData<List<Transaksi>> = _data

    init {
        _data.value = getDataDummy()
    }

    fun addData(newTransaksi: Transaksi) {
        val updatedList = _data.value.orEmpty() + newTransaksi
        _data.value = updatedList
    }

    private fun getDataDummy() : List<Transaksi>{
        val dataTransaksi = mutableListOf<Transaksi>()
        for (i in 1 until 10){
            dataTransaksi.add(
                Transaksi(
                    i.toLong(),
                    i.toInt(),
                    "Ini ke-$i",
                    "Pemasukan",
                    "$i/03/2024"
                )
            )
        }
        return dataTransaksi
    }

}
