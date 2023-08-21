package br.com.fiap.todoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TASK_MODEL_TABLE_NAME = "taskTable"
@Entity(tableName = TASK_MODEL_TABLE_NAME)
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val status: TaskStatus
)

enum class TaskStatus(val title: String) {
    PENDING("Pendente"), PROGRESS("Em andamento"), COMPLETED("Concluída");

    companion object {
        fun getByTitle(title: String): TaskStatus {
            return values().find {
                it.title == title
            } ?: PENDING
        }
    }
}
