package org.d3if0043.monefysafe.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0043.monefysafe.model.Transaksi

@Dao
interface TransaksiDao {
    @Insert
    suspend fun insert(transaksi: Transaksi)

    @Update
    suspend fun update(transaksi: Transaksi)

    @Query("SELECT * FROM transaksi ORDER BY id ASC")
    fun getTransaksi(): Flow<List<Transaksi>>

    @Query("SELECT * FROM transaksi WHERE id = :id")
    suspend fun getTransaksiById(id: Long): Transaksi?

    @Query("DELETE FROM transaksi WHERE id = :id")
    suspend fun deleteById(id: Long)
}