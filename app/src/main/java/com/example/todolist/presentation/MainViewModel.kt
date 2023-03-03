package com.example.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.RetrofitHelper
import com.example.todolist.TodoApi
import com.example.todolist.domian.MainRepository
import com.example.todolist.data.models.ResultData
import com.example.todolist.data.models.TaskData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repo = MainRepository(RetrofitHelper.getInstance().create(TodoApi::class.java))

    val getAllTasksFlow = MutableSharedFlow<List<TaskData>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    suspend fun getAllTasks() {
        repo.getAllTasks().onEach {
            when (it) {
                is ResultData.Success -> {
                    getAllTasksFlow.emit(it.data.payload)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}