package com.example.real_timedeliverytrackingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.real_timedeliverytrackingapp.R
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.real_timedeliverytrackingapp.viewModels.AuthState
import com.example.real_timedeliverytrackingapp.viewModels.AuthViewModel
import com.example.real_timedeliverytrackingapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.signUp(email, password)
        }

        viewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                AuthState.AUTHENTICATED -> findNavController().navigate(R.id.action_registerFragment_to_deliveryListFragment)
                AuthState.ERROR -> Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }
}