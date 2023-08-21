package br.com.fiap.todoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.fiap.todoapp.database.AppDatabase
import br.com.fiap.todoapp.database.TaskModel
import br.com.fiap.todoapp.database.TaskStatus

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    val selectedFilters: MutableSet<TaskStatus> = mutableSetOf()

    private val appDb by lazy {
        AppDatabase.getDatabase(application.applicationContext)
    }

    fun selectAll(): List<TaskModel> {
        return appDb.taskDAO().selectAll()
    }

    fun selectByStatus(status: List<TaskStatus>): List<TaskModel> {
        return appDb.taskDAO().selectByStatus(status)
    }
}