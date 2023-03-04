package com.example.todolist.ui.all

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.RetrofitHelper
import com.example.todolist.TodoApi
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.presentation.MainViewModel
import com.example.todolist.ui.adapters.TasksAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val adapter = TasksAdapter()
    private lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]

        val retrofit = RetrofitHelper.getInstance()
        val api = retrofit.create(TodoApi::class.java)

        lifecycleScope.launchWhenResumed {
            viewModel.getAllTasks()
        }

        initListeners()
        initObservers()
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
                lifecycleScope.launchWhenResumed {
                    viewModel.delete()
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.getAllTasksFlow.onEach {

        }.launchIn(lifecycleScope)

        viewModel.updateTasksFlow.onEach {

        }

        viewModel.deleteTasksFlow.onEach {

        }
    }
}