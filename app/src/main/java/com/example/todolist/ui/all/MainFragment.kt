package com.example.todolist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
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

        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]

        lifecycleScope.launchWhenResumed {
            viewModel.getAllTasks()
        }

        initListeners()
        initObservers()

    }

    private fun initListeners() {
        binding.apply {

            fabAddTask.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToAddFragment()
                )
            }

            adapter.setOnItemClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToUpdateFragment(it.id,it.task,it.description,it.is_done)
                )
            }

        }
    }

    private fun initObservers() {
        viewModel.getAllTasksFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Qa'te islendi")
        }
    }
}