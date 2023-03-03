package com.example.todolist.data.models

data class TaskData(
    val id: Int,
    val task: String,
    val description: String,
    val is_done: Boolean
)