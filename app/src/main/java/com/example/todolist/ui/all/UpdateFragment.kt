package com.example.todolist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment(R.layout.fragment_update) {
    private lateinit var binding: FragmentUpdateBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)

    }
}