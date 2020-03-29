package com.galinc.worklist.domain.repository

interface DataBaseRepository{
    fun addTaskToDB(textOfTask:String)
}