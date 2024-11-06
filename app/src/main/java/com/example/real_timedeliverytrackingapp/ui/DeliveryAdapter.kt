package com.example.real_timedeliverytrackingapp.ui

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.real_timedeliverytrackingapp.data_model.Delivery
import com.example.real_timedeliverytrackingapp.databinding.ItemDeliveryBinding

class DeliveryAdapter(private val onItemClick: (Delivery) -> Unit) :
    ListAdapter<Delivery, DeliveryAdapter.DeliveryViewHolder>(DeliveryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding = ItemDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeliveryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        val delivery = getItem(position)
        holder.bind(delivery)
    }

    inner class DeliveryViewHolder(private val binding: ItemDeliveryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(delivery: Delivery) {
            binding.textViewDeliveryId.text = delivery.id
            binding.textViewExpectedDelivery.text = delivery.expectedDeliveryDate
            binding.buttonTrack.setOnClickListener { onItemClick(delivery) }
        }
    }

    class DeliveryDiffCallback : DiffUtil.ItemCallback<Delivery>() {
        override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            return oldItem == newItem
        }
    }
}