package br.com.fiap.todoapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDAO {

    @Query("SELECT * FROM $TASK_MODEL_TABLE_NAME WHERE status == :statusParam")
    fun selectByStatus(statusParam: TaskStatus): List<TaskModel>

    @Query("SELECT * FROM $TASK_MODEL_TABLE_NAME")
    fun selectAll(): List<TaskModel>

    @Insert
    fun insert(taskModel: TaskModel)
}