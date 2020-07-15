package com.galinc.worklist.data


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.galinc.worklist.db.AppDatabase
import com.galinc.worklist.db.dao.MainTaskDao
import com.galinc.worklist.db.entities.MainTaskDB
import com.galinc.worklist.domain.entity.MainTask
import com.galinc.worklist.domain.repository.DataBaseRepository
import com.galinc.worklist.mapper.transform

import io.reactivex.schedulers.Schedulers


class DataBaseRepositoryImpl(context: Context) : DataBaseRepository {

    private val mainTaskDao:MainTaskDao

    override fun updateMainTask(mainTask: MainTask) {
        mainTaskDao.updateMainTask(mainTask.transform()).subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getMainTaskByGuid(guid: String): LiveData<MainTask> {
        return  Transformations.map(mainTaskDao.getMainTaskByGuidLiveData(guid)){
            it.transform()
        }
    }

    init {
        val db: AppDatabase = AppDatabase.getInstance(context)
        mainTaskDao = db.mainTaskDao()
        //mAllWords = mainTaskDao.getAlphabetizedWords()
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
        mainTaskDao.insertMainTask(MainTaskDB(text = textOfTask,checked = false , title = "")).subscribeOn(
            Schedulers.io()).subscribe()
    }
}
