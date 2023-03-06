package com.example.todolist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.utils.toast
import com.example.todolist.R
import com.example.todolist.retrofit.RetrofitHelper
import com.example.todolist.retrofit.TodoApi
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.presentation.MainViewModel
import com.example.todolist.ui.adapters.TaskAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val adapter = TaskAdapter()
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

        initListeners()
        initObservers()

        lifecycleScope.launchWhenResumed {
            viewModel.getAllTasks()
        }
    }

    private fun initListeners() {
        binding.apply {
            recyclerView.adapter = adapter

            fabAddTask.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToAddFragment()
                )
            }

            adapter.setOnItemClickListener { id, task, desc ->
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToUpdateFragment(id,task,desc)
                )
            }

        }
    }

    private fun initObservers() {
        viewModel.getAllTasksFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.updateTasksFlow.onEach {

        }

        viewModel.messageFlow.onEach {
            toast("Qa'te islendi")
        }
    }

    override fun onStop() {
        super.onStop()

        lifecycleScope.launchWhenResumed {
            viewModel.getAllTasks()
        }
    }
}