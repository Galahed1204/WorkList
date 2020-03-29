package com.galinc.worklist.domain.entity

class MainTask(
                    var textOfTask:String,
                    var completed:Boolean){
    val guid:String? = null
    constructor( guid:String,
                 textOfTask:String,
                 completed:Boolean):this( textOfTask,
    completed)
}