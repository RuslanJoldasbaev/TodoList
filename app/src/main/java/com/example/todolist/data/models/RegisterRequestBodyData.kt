package com.example.todolist.data.models

data class RegisterRequestBodyData(
    val name: String,
    val password: String,
    val phone: String
)