package com.example.todolist.domian

import com.example.todolist.retrofit.TodoApi
import com.example.todolist.data.local.LocalStorage
import com.example.todolist.data.models.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository(val api: TodoApi) {

    suspend fun getAllTasks() = flow {
        val response = api.getAllTasks("Bearer ${LocalStorage().token}")
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun addTasks(name: String, description: String) = flow {
        val response =
            api.addTasks(CreateTaskBodyData(name, description), "Bearer ${LocalStorage().token}")
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun updateTasks(isDone: Boolean, id: Int) {
        api.updateTasks( "Bearer ${LocalStorage().token}", DoneRequestBodyData(isDone),id)
    }

    suspend fun deleteTasks(id: Int) {
        api.deleteTasks("Bearer ${LocalStorage().token}", id)
    }

    suspend fun register(name: String, password: String, phone: String) = flow {

        val response =
            api.registerUser(RegisterRequestBodyData(name, password, phone))
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun login(phone: String, password: String) = flow {

        val response = api.loginUser(LoginData(password, phone))
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }
}