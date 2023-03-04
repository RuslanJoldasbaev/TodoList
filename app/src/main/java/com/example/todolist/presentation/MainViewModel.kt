package com.example.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.RetrofitHelper
import com.example.todolist.TodoApi
import com.example.todolist.data.models.*
import com.example.todolist.domian.MainRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repo = MainRepository(RetrofitHelper.getInstance().create(TodoApi::class.java))

    val getAllTasksFlow = MutableSharedFlow<List<TaskData>>()
    val updateTasksFlow = MutableSharedFlow<List<TaskData>>()
    val deleteTasksFlow = MutableSharedFlow<List<TaskData>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()
    val registerFlow = MutableSharedFlow<RegisterResponseData>()
    val loginFlow = MutableSharedFlow<RegisterResponseData>()

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

    suspend fun update() {
        repo.updateTasks().onEach {
            when (it) {
                is ResultData.Success -> {
                    updateTasksFlow.emit(it.data.payload)
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

    suspend fun delete() {
        repo.deleteTasks().onEach {
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

    suspend fun register(name: String, password: String, phone: String) {
        repo.register(name, password, phone).onEach {
            when (it) {
                is ResultData.Success -> {
                    registerFlow.emit(it.data.payload)
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

    suspend fun login(password: String, phone: String) {
        repo.login(phone, password).onEach {
            when (it) {
                is ResultData.Success -> {
                    loginFlow.emit(it.data.payload)
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