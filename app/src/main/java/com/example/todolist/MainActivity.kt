package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitHelper.getInstance()

        val api = retrofit.create(TodoApi::class.java)

        val token = "34|vBwGHJXqZLYhulkoO4hbBZ7Ak4DOCIMPwctlDa2E"
        lifecycleScope.launchWhenResumed {
            val response = api.getAllTasks("Bearer $token")
            if (response.isSuccessful) {
                Log.d("TTTT", "${response.body()}")
            } else {
                Log.d("TTTT", "Message: ${response.message()}")
            }
        }
    }
}