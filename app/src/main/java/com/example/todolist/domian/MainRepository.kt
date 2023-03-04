package com.example.todolist.domian

import com.example.todolist.TodoApi
import com.example.todolist.data.local.LocalStorage
import com.example.todolist.data.models.LoginData
import com.example.todolist.data.models.RegisterRequestBodyData
import com.example.todolist.data.models.ResultData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository(val api: TodoApi) {
    suspend fun getAllTasks() = flow {
        val token = "asdkajsdabnskljaskjld"

        val response = api.getAllTasks("Bearer $token")
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun updateTasks() = flow {
        val token = "asdkajsdabnskljaskjld"

        val response = api.updateTasks("Bearer $token")
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun deleteTasks() = flow {
        val token = "asdkajsdabnskljaskjld"
        val id = 0
        val response = api.deleteTasks("Bearer ${LocalStorage().token}",id)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
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

    suspend fun login(phone:String,password: String) = flow {

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