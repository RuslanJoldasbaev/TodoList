package com.example.todolist.ui.all

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.utils.toast
import com.example.todolist.R
import com.example.todolist.databinding.FragmentAddBinding
import com.example.todolist.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddFragment : Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]

        initListeners()
        initObservers()

    }

    private fun initListeners() {
        binding.apply {

            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnAdd.setOnClickListener {
                val name = etTaskName.text.toString()
                val description = etTaskDescription.text.toString()


                if (name.isNotEmpty() && description.isNotEmpty()) {
                    lifecycleScope.launchWhenResumed {
                        viewModel.addTasks(name, description)
                        findNavController().popBackStack()
                    }
                } else {
                    Toast.makeText(requireContext(), "Kesteni toltir", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.addTasksFlow.onEach {
            viewModel.getAllTasks()
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Belgisiz qa'telik payda boldi")
        }.launchIn(lifecycleScope)
    }
}