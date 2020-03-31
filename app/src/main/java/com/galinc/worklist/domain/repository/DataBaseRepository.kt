package com.galinc.worklist.domain.repository

import androidx.lifecycle.LiveData
import com.galinc.worklist.domain.entity.MainTask

interface DataBaseRepository{
    fun addTaskToDB(textOfTask:String)

    fun getAllMainTask():LiveData<List<MainTask>>
}