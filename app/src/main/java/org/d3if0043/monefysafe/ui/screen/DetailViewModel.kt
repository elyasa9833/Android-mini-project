package org.d3if0043.monefysafe.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0043.monefysafe.database.TransaksiDao
import org.d3if0043.monefysafe.model.Transaksi
import java.text.SimpleDateFormat
import java.util.Locale

class DetailViewModel(private val dao: TransaksiDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(jumlah: String, keterangan: String, jenis: String){
        val transaksi = Transaksi(
            jumlah = jumlah.toInt(),
            keterangan = keterangan,
            jenis = jenis,
            tanggal = formatter.format(System.currentTimeMillis())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(transaksi)
        }
    }
}