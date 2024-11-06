package com.example.real_timedeliverytrackingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels

import com.example.real_timedeliverytrackingapp.databinding.FragmentProfileBinding
import com.example.real_timedeliverytrackingapp.viewModels.ProfileState
import com.example.real_timedeliverytrackingapp.viewModels.ProfileViewModel

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.editTextName.setText(user.name)
            binding.editTextPhone.setText(user.phone)
        }

        binding.buttonUpdateProfile.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val phone = binding.editTextPhone.text.toString()
            viewModel.updateProfile(name, phone)
        }

        viewModel.profileState.observe(viewLifecycleOwner) { state ->
            when (state) {
                ProfileState.LOADING -> binding.progressBar.visibility = View.VISIBLE
                ProfileState.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                }
                ProfileState.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
