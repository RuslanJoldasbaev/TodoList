package com.example.todolist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.ui.adapters.TasksAdapter

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val adapter = TasksAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

    }

    private fun initListeners() {
        binding.apply {
            recyclerView.adapter = adapter

            fabAddTask.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToAddTaskDialog()
                )
            }
            adapter.setOnDeleteClickListener { taskData, position ->

            }
        }
    }
}