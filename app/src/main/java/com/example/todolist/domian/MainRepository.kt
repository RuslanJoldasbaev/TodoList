package com.example.todolist.domian

import com.example.todolist.TodoApi
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
}