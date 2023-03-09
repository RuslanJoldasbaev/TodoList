package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todolist.data.local.LocalStorage
import com.example.todolist.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSplashBinding.bind(view)

        if (LocalStorage().isRegister) {
            lifecycleScope.launchWhenResumed {
                binding.infoLogo.playAnimation()
                Handler().postDelayed({
                    findNavController().navigate(
                        SplashFragmentDirections.actionSplashFragmentToMainFragment()
                    )
                }, 2000)
            }
        } else {
            lifecycleScope.launchWhenResumed {
                binding.infoLogo.playAnimation()
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                )
                Handler().postDelayed({
                }, 2000)
            }
        }
    }
}