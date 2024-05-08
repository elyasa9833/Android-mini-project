package org.d3if0043.monefysafe.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if0043.monefysafe.model.Transaksi

@Database(entities = [Transaksi::class], version = 1, exportSchema = false)
abstract class TransaksiDb : RoomDatabase() {
    abstract val dao: TransaksiDao

    companion object {
        @Volatile
        private  var INSTANCE: TransaksiDb? = null

        fun getInstance(context: Context): TransaksiDb {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TransaksiDb::class.java,
                        "transaksi.db"
                    ).build()
                    INSTANCE =instance
                }
                return instance
            }
        }
    }

}