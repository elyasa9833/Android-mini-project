package org.d3if0043.monefysafe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaksi")
data class Transaksi(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val jumlah: Int,
    val keterangan: String,
    val jenis: String,
    val tanggal: String
)
