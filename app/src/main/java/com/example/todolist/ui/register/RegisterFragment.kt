package com.example.todolist.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            registerButton.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
}