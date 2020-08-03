package com.galinc.worklist.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.galinc.worklist.db.entities.MainTaskDB
import io.reactivex.Completable

@Dao
interface MainTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMainTask(mainTaskDB: MainTaskDB):Completable

    @Query("SELECT * from task where checked = 0")
    fun getMainTaskDBLiveData(): LiveData<List<MainTaskDB>>

    @Query("SELECT * from task")
    fun getMainTaskDB(): List<MainTaskDB>

    @Update
    fun updateMainTask(mainTaskDB: MainTaskDB):Completable

    @Query("SELECT * from task WHERE guid = :guid")
    fun getMainTaskByGuidLiveData(guid:String):LiveData<MainTaskDB>

    @Query("SELECT * from task where checked = 0 ORDER BY title ASC, isHeader DESC")
    fun getMainTaskWithHeaderDBLiveData(): LiveData<List<MainTaskDB>>
}