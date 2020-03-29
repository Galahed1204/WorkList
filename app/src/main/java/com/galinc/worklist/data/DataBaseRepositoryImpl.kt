package com.galinc.worklist.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.galinc.worklist.db.AppDatabase
import com.galinc.worklist.db.dao.MainTaskDao
import com.galinc.worklist.db.entities.MainTaskDB
import com.galinc.worklist.domain.repository.DataBaseRepository


class DataBaseRepositoryImpl(context: Context) : DataBaseRepository {
    private val mainTaskDao:MainTaskDao

    init {
        val db: AppDatabase = AppDatabase.getInstance(context)
        mainTaskDao = db.mainTaskDao()
        //mAllWords = mainTaskDao.getAlphabetizedWords()
    }
    override fun addTaskToDB(textOfTask: String) {
        mainTaskDao.insertMainTask(MainTaskDB(text = textOfTask,checked = false ))
    }
}
