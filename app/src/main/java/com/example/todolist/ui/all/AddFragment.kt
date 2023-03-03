package com.example.todolist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.todolist.R
import com.example.todolist.databinding.DialogTaskAddBinding

class AddFragment : DialogFragment(R.layout.fragment_add) {
    private lateinit var binding: DialogTaskAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogTaskAddBinding.bind(view)

    }
}