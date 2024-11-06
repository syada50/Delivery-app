package com.example.real_timedeliverytrackingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.real_timedeliverytrackingapp.R
import com.example.real_timedeliverytrackingapp.viewModels.DeliveryViewModel
import com.example.real_timedeliverytrackingapp.databinding.FragmentDeliveryListBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager


class DeliveryListFragment : Fragment() {

    private val viewModel: DeliveryViewModel by viewModels()
    private var _binding: FragmentDeliveryListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DeliveryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDeliveryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the adapter with navigation action
        adapter = DeliveryAdapter { delivery ->
            val action = DeliveryListFragmentDirections.actionDeliveryListFragmentToTrackingFragment(delivery.id)
            findNavController().navigate(action)
        }

        // Set up RecyclerView
        binding.recyclerViewDeliveries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@DeliveryListFragment.adapter
        }

        // Observe deliveries from ViewModel
        viewModel.deliveries.observe(viewLifecycleOwner) { deliveries ->
            adapter.submitList(deliveries)
        }

        // Set up Profile button navigation
        binding.buttonProfile.setOnClickListener {
            findNavController().navigate(R.id.action_deliveryListFragment_to_profileFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear binding reference to avoid memory leaks
    }
}

