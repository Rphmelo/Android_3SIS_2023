package br.com.fiap.todoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.fiap.todoapp.database.AppDatabase
import br.com.fiap.todoapp.database.TaskModel
import br.com.fiap.todoapp.database.TaskStatus

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    var selectedFilter: TaskStatus? = null
    val snackBarStatus: MutableLiveData<TaskStatus> = MutableLiveData()

    private val appDb by lazy {
        AppDatabase.getDatabase(application.applicationContext)
    }

    fun selectAll(): List<TaskModel> {
        return appDb.taskDAO().selectAll()
    }

    fun selectByStatus(status: TaskStatus): List<TaskModel> {
        snackBarStatus.postValue(status)
        return appDb.taskDAO().selectByStatus(status)
    }
}