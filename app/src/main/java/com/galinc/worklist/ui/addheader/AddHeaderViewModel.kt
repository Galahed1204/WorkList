package com.galinc.worklist.ui.addheader

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.galinc.worklist.data.DataBaseRepositoryImpl
import com.galinc.worklist.domain.entity.MainTaskWithHeader
import com.galinc.worklist.domain.repository.DataBaseRepository

class AddHeaderViewModel(application: Application) : AndroidViewModel(application)  {

    private var mRepository: DataBaseRepository? = null
    val guidLiveData = MutableLiveData<String>().apply {
        value = ""
    }

    init {
        mRepository = DataBaseRepositoryImpl(application)
    }

    fun addHeader(text:String){
        mRepository?.addHeaderToDB(text)
    }

    fun getHeader(guid:String) =
        mRepository?.getMainTaskWithHeaderByGuid(guid)

    fun updateHeader(mainTask: MainTaskWithHeader){
        mRepository?.updateMainTaskWithHeader(mainTask)
    }
}