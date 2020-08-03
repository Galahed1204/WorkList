package com.galinc.worklist.mapper

import com.galinc.worklist.db.entities.MainTaskDB
import com.galinc.worklist.domain.entity.MainTask
import java.util.*

fun MainTask.transform() = MainTaskDB(
    guid = this.guid?: UUID.randomUUID().toString(),
    text = this.textOfTask,
    checked = this.completed,
    title = "",
    isHeader = false

)

fun MainTaskDB.transform() = MainTask(
    guid = this.guid,
    textOfTask = this.text,
    completed = this.checked
)