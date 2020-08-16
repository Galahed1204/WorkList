package com.galinc.worklist.ui.addtask.header

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.galinc.worklist.data.DataBaseRepositoryImpl
import com.galinc.worklist.domain.entity.MainTaskWithHeader
import com.galinc.worklist.domain.repository.DataBaseRepository

class AddTaskWithHeaderViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: DataBaseRepository? = null
    val guidLiveData = MutableLiveData<String>().apply {
        value = ""
    }
    val headerLiveData = MutableLiveData<String>().apply {
        value = ""
    }

    init {
        mRepository = DataBaseRepositoryImpl(application)
    }

    fun addTask(text:String,header:String){
        mRepository?.addTaskWithHeaderToDB(text,header)
    }

    fun getTask(text:String) =
        mRepository?.getMainTaskWithHeaderByGuid(text)

    fun updateTask(mainTask: MainTaskWithHeader){
        mRepository?.updateMainTaskWithHeader(mainTask)
    }

    fun getHeaders()= mRepository?.getAllHeaders()

}