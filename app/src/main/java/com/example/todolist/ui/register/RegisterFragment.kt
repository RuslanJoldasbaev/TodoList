package com.example.todolist.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.utils.toast
import com.example.todolist.R
import com.example.todolist.data.local.LocalStorage
import com.example.todolist.databinding.FragmentRegisterBinding
import com.example.todolist.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]

        LocalStorage().isRegister = false

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.apply {
            registerButton.setOnClickListener {
                val number = etPhone.text.toString()
                val name = etName.text.toString()
                val password = etPassword.text.toString()

                if (number.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty()) {
                    lifecycleScope.launchWhenResumed {
                        viewModel.register(name,password, number)
                    }
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.registerFlow.onEach {
            val token = it.token
            LocalStorage().token = token
            LocalStorage().isRegister = true
            LocalStorage().isDone = false
            findNavController().popBackStack()
        }.launchIn(lifecycleScope)


        viewModel.messageFlow.onEach {
            toast("Siz registerdan otip bolg'ansiz")
        }.launchIn(lifecycleScope)
    }
}