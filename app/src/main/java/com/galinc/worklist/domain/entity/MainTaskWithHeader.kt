package com.galinc.worklist.domain.entity

class MainTaskWithHeader(val guid:String? = null,
                         var textOfTask:String,
                         var completed:Boolean,
                         var header:String,
                         var isHeader:Boolean) {
}