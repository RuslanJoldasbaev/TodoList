package com.example.todolist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todolist.data.local.LocalStorage
import kotlinx.coroutines.delay

class SplashFragment : Fragment(R.layout.fragment_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (LocalStorage().isRegister) {
            lifecycleScope.launchWhenResumed {
                delay(2000)
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToMainFragment()
                )
            }
        } else {
           lifecycleScope.launchWhenResumed {
               delay(2000)
               findNavController().navigate(
                   SplashFragmentDirections.actionSplashFragmentToLoginFragment()
               )
           }
        }
    }
}