package com.galinc.worklist.ui.addtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.galinc.worklist.data.DataBaseRepositoryImpl
import com.galinc.worklist.domain.repository.DataBaseRepository


class AddTaskViewModel(application: Application ) : AndroidViewModel(application) {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is send Fragment"
//    }
//    val text: LiveData<String> = _text

    private var mRepository: DataBaseRepository? = null


    init {
        //super(application)
        mRepository = DataBaseRepositoryImpl(application)
    }

    fun addTask(text:String){
        mRepository?.addTaskToDB(text)
    }

//    fun WordViewModel(application: Application?) {
//        super(application)
//        mRepository = WordRepository(application)
//        mAllWords = mRepository.getAllWords()
//    }
}