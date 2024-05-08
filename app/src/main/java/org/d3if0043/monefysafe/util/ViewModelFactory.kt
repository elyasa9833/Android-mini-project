package org.d3if0043.monefysafe.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0043.monefysafe.database.TransaksiDao
import org.d3if0043.monefysafe.ui.screen.DetailViewModel
import org.d3if0043.monefysafe.ui.screen.MainViewModel

class ViewModelFactory(private val dao: TransaksiDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(dao) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}