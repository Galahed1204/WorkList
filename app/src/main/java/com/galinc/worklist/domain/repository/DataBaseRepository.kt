package com.galinc.worklist.domain.repository

import androidx.lifecycle.LiveData
import com.galinc.worklist.domain.entity.MainTask
import com.galinc.worklist.domain.entity.MainTaskWithHeader

interface DataBaseRepository{
    fun addTaskToDB(textOfTask:String)

    fun addHeaderToDB(header:String)

    fun getAllMainTask():LiveData<List<MainTask>>

    fun updateMainTask(mainTask: MainTask)

    fun getMainTaskByGuid(guid:String):LiveData<MainTask>

    fun getAllMainTaskWithHeader():LiveData<List<MainTaskWithHeader>>

    fun updateMainTaskWithHeader(mainTaskWithHeader: MainTaskWithHeader)

    fun getMainTaskWithHeaderByGuid(guid:String):LiveData<MainTaskWithHeader>
}