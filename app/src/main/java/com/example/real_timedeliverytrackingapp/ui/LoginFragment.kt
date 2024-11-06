package com.example.real_timedeliverytrackingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.real_timedeliverytrackingapp.R.id.action_loginFragment_to_deliveryListFragment
import com.example.real_timedeliverytrackingapp.R.id.action_loginFragment_to_registerFragment
import com.example.real_timedeliverytrackingapp.viewModels.AuthState
import com.example.real_timedeliverytrackingapp.viewModels.AuthViewModel
import com.example.real_timedeliverytrackingapp.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.signIn(email, password)
        }

        binding.textViewRegister.setOnClickListener {
            findNavController().navigate(action_loginFragment_to_registerFragment)
        }

        viewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                AuthState.AUTHENTICATED -> findNavController().navigate(
                    action_loginFragment_to_deliveryListFragment
                )
                AuthState.ERROR -> Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }
}