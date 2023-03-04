package com.example.todolist.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.utils.toast
import com.example.todolist.R
import com.example.todolist.data.local.LocalStorage
import com.example.todolist.databinding.FragmentLoginBinding
import com.example.todolist.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

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
            loginButton.setOnClickListener {
                val password = etPassword.text.toString()
                val number = etPhone.text.toString()

                if (password.isNotEmpty() && number.isNotEmpty()) {
                    lifecycleScope.launchWhenResumed {
                        viewModel.login(password, number)
                    }
                }
            }

            registerButton.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
            }
        }
    }

    private fun initObservers() {
        viewModel.loginFlow.onEach {
            LocalStorage().token = it.token
            LocalStorage().isRegister = true
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToMainFragment()
            )
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Siz registraciyadan o'tpegensiz")
        }.launchIn(lifecycleScope)
    }
}