package com.mayur.shaadiapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mayur.shaadiapp.database.NameEntity
import com.mayur.shaadiapp.database.NameRoomDb

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var allNames: MutableLiveData<List<NameEntity>>

    init {
        allNames = MutableLiveData()
        getAllNames()
    }

    fun getAllNamesObservers(): MutableLiveData<List<NameEntity>> {
        return allNames
    }

    fun getAllNames() {
        val nameDoa = NameRoomDb.getAppDatabase(getApplication())?.nameDao()
        val list = nameDoa?.getAllNameInfo()
        allNames.postValue(list)

    }

    fun insertNameInfo(entity: NameEntity) {
        val nameDoa = NameRoomDb.getAppDatabase(getApplication())?.nameDao()
        nameDoa?.insertNameList(entity)
        getAllNames()
    }

    fun updateStatus(statusStr: String, id: Int) {
        val nameDoa = NameRoomDb.getAppDatabase(getApplication())?.nameDao()
        nameDoa?.updateStatus(statusStr, id)
        getAllNames()
    }


}