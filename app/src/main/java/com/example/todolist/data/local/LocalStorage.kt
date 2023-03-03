package com.example.todolist.data.local

import android.content.Context
import com.example.chatappwithfirebase.utils.StringPreference
import com.example.todolist.app.App

class LocalStorage {

    companion object {
        val pref =
            App.instance.getSharedPreferences("ChatAppSharedPref", Context.MODE_PRIVATE)
    }

    var username by StringPreference(pref, "temp001")
}