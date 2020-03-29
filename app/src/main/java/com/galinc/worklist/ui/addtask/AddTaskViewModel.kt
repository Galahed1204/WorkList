package com.galinc.worklist.ui.addtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.galinc.worklist.domain.repository.DataBaseRepository


class AddTaskViewModel(application: Application ) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is send Fragment"
    }
    val text: LiveData<String> = _text

    private var mRepository: DataBaseRepository? = null

//    fun WordViewModel(application: Application?) {
//        super(application)
//        mRepository = WordRepository(application)
//        mAllWords = mRepository.getAllWords()
//    }
}