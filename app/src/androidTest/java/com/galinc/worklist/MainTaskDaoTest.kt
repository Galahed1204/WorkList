package com.galinc.worklist

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.galinc.worklist.db.AppDatabase
import com.galinc.worklist.db.entities.MainTaskDB
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainTaskDaoTest {
    private lateinit var database: AppDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java)
            // allowing main thread queries, just for testing
            .allowMainThreadQueries()
            .build()

        //database = MainDatabase.getInstance(ApplicationProvider.getApplicationContext())
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun signInTest() {

        database.mainTaskDao().insertMainTask(MainTaskDB(text = "test1",checked = false))
            .blockingAwait()

        assert(database.mainTaskDao().getMainTaskDB().size == 1)


    }

}