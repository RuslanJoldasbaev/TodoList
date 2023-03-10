package com.example.todolist.data.local

import android.content.Context
import com.example.chatappwithfirebase.utils.BooleanPreference
import com.example.chatappwithfirebase.utils.StringPreference
import com.example.todolist.app.App

class LocalStorage {

    companion object {
        val pref =
            App.instance.getSharedPreferences("ChatAppSharedPref", Context.MODE_PRIVATE)
    }

    var token by StringPreference(pref)

    var isRegister by BooleanPreference(pref)

    var isDone by BooleanPreference(pref, false)
}