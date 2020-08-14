package com.galinc.worklist.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.galinc.worklist.data.DataBaseRepositoryImpl
import com.galinc.worklist.domain.repository.DataBaseRepository
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.galinc.worklist.domain.entity.MainTask
import com.galinc.worklist.domain.entity.MainTaskWithHeader


class HomeViewModel(application: Application) : AndroidViewModel(application) {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
    private var mRepository: DataBaseRepository = DataBaseRepositoryImpl(application)
    private var mAllMainTask: LiveData<List<MainTask>>? = null
    private var mAllMainTaskWithHeader: LiveData<List<MainTaskWithHeader>>? = null

    init {
        mAllMainTask = mRepository.getAllMainTask()
        mAllMainTaskWithHeader = mRepository.getAllMainTaskWithHeader()
    }

    fun getAllMainTask() = mAllMainTask

    fun getAllMainTaskWithHeader() = mAllMainTaskWithHeader

    fun updateMainTask(mainTask: MainTask){
        mRepository.updateMainTask(mainTask)
    }

    fun updateMainTaskWithHeader(mainTaskWithHeader: MainTaskWithHeader){
        mRepository.updateMainTaskWithHeader(mainTaskWithHeader)
    }





}