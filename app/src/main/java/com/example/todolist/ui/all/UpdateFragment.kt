package com.example.todolist.ui.all

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chatapp.utils.toast
import com.example.todolist.R
import com.example.todolist.data.local.LocalStorage
import com.example.todolist.databinding.FragmentUpdateBinding
import com.example.todolist.presentation.MainViewModel
import kotlinx.coroutines.flow.onEach

class UpdateFragment : Fragment(R.layout.fragment_update) {
    private lateinit var binding: FragmentUpdateBinding
    private val navArgs: UpdateFragmentArgs by navArgs()
    private lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]

        val id = navArgs.id
        val task = navArgs.task
        val desc = navArgs.desc
        val bool = navArgs.bool

        Log.d("TTTT", "$bool")

        binding.apply {
            if (bool) {
                btnIsDone.text = "done"
            } else btnIsDone.text = "not done"

            btnIsDone.setOnClickListener {
                lifecycleScope.launchWhenResumed {
                    if (bool) {
                        viewModel.update(false, id)
                    } else {
                        viewModel.update(true, id)
                    }
                }
                findNavController().popBackStack()
            }
        }

        initListeners(id, task, desc)
    }

    private fun initListeners(id: Int, task: String, desc: String) {
        binding.apply {
            etName.text = task
            etDescription.text = desc

            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnIsDelete.setOnClickListener {
                lifecycleScope.launchWhenResumed {
                    viewModel.delete(id)
                    findNavController().popBackStack()
                }
            }
        }
    }

}