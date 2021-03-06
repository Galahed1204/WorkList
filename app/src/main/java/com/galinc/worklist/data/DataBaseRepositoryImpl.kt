package com.galinc.worklist.data


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.galinc.worklist.db.AppDatabase
import com.galinc.worklist.db.dao.MainTaskDao
import com.galinc.worklist.db.entities.MainTaskDB
import com.galinc.worklist.domain.entity.MainTask
import com.galinc.worklist.domain.entity.MainTaskWithHeader
import com.galinc.worklist.domain.repository.DataBaseRepository
import com.galinc.worklist.mapper.transform
import com.galinc.worklist.mapper.transformH

import io.reactivex.schedulers.Schedulers


class DataBaseRepositoryImpl(context: Context) : DataBaseRepository {
    private val mainTaskDao:MainTaskDao

    init {
        val db: AppDatabase = AppDatabase.getInstance(context)
        mainTaskDao = db.mainTaskDao()
    }

    override fun updateMainTask(mainTask: MainTask) {
        mainTaskDao.updateMainTask(mainTask.transform()).subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getMainTaskByGuid(guid: String): LiveData<MainTask> {
        return  Transformations.map(mainTaskDao.getMainTaskByGuidLiveData(guid)){
            it.transform()
        }
    }

    override fun getAllMainTaskWithHeader(): LiveData<List<MainTaskWithHeader>> {
        return Transformations.map(
            mainTaskDao.getMainTaskDBLiveData()){list ->
            list.map {
                    task -> task.transformH()
            }
        }
    }

    override fun updateMainTaskWithHeader(mainTaskWithHeader: MainTaskWithHeader) {
        mainTaskDao.updateMainTask(mainTaskWithHeader.transform()).subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getMainTaskWithHeaderByGuid(guid: String): LiveData<MainTaskWithHeader> {
        return  Transformations.map(mainTaskDao.getMainTaskByGuidLiveData(guid)){
            it.transformH()
        }
    }

    override fun getAllMainTask(): LiveData<List<MainTask>> {
        return Transformations.map(
            mainTaskDao.getMainTaskDBLiveData()){list ->
            list.map {
                task -> task.transform()
            }
        }

    }

    override fun addTaskToDB(textOfTask: String) {
        mainTaskDao.insertMainTask(MainTaskDB(text = textOfTask,checked = false , title = "",isHeader = false)).subscribeOn(
            Schedulers.io()).subscribe()
    }
    override fun addHeaderToDB(header: String) {
        mainTaskDao.insertMainTask(MainTaskDB(text = "",checked = false , title = header,isHeader = true)).subscribeOn(
            Schedulers.io()).subscribe()
    }

    override fun addTaskWithHeaderToDB(textOfTask: String, header: String) {
        mainTaskDao.insertMainTask(MainTaskDB(text = textOfTask,checked = false , title = header,isHeader = false)).subscribeOn(
            Schedulers.io()).subscribe()
    }

    override fun getAllHeaders(): LiveData<List<String>> {
        return mainTaskDao.getHeadersLiveData()
    }
}
