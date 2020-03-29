package com.galinc.worklist.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task")
data class MainTaskDB (
    @PrimaryKey @ColumnInfo(name = "guid",index = true)
    val guid: String = UUID.randomUUID().toString(),
    val text:String,
    val checked:Boolean)