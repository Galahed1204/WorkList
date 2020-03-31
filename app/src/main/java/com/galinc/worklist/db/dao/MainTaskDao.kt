package com.galinc.worklist.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.galinc.worklist.db.entities.MainTaskDB
import io.reactivex.Completable

@Dao
interface MainTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMainTask(mainTaskDB: MainTaskDB)//:Completable

    @Query("SELECT * from task")
    fun getMainTaskDBLiveData(): LiveData<List<MainTaskDB>>

}