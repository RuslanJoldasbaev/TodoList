package com.example.todolist

import com.example.todolist.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface TodoApi {

    @GET("/api/tasks")
    suspend fun getAllTasks(@Header("Authorization") token: String): Response<GenericData<List<TaskData>>>

    @POST("/api/register")
    suspend fun registerUser(@Body body: RegisterRequestBodyData): Response<GenericData<RegisterResponseData>>

    @POST("/api/login")
    suspend fun loginUser(@Body body: LoginData): Response<GenericData<RegisterResponseData>>

    @PUT("/api/tasks")
    suspend fun updateTasks(
        @Header("Authorization") token: String,
    ): Response<GenericData<List<TaskData>>>

    @DELETE("/api/tasks/{id}")
    suspend fun deleteTasks(
        @Header("Authorization") token: String,
        @Path("id")
        id: Int
    ): Response<GenericData<List<TaskData>>>
}