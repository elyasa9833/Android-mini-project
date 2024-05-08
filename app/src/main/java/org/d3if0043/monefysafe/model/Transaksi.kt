package org.d3if0043.monefysafe.model

data class Transaksi(
    val id: Long,
    val jumlah: Int,
    val keterangan: String,
    val jenis: String,
    val tanggal: String
)
