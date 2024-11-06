package com.example.real_timedeliverytrackingapp.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.real_timedeliverytrackingapp.data_model.Delivery
import com.example.real_timedeliverytrackingapp.repository.AuthRepository
import com.example.real_timedeliverytrackingapp.repository.DeliveryRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import javax.inject.Inject


class DeliveryViewModel @Inject constructor(
    private val deliveryRepository: DeliveryRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _deliveries = MutableLiveData<List<Delivery>>()
    val deliveries: LiveData<List<Delivery>> = _deliveries

    private val _deliveryLocation = MutableLiveData<LatLng>()
    val deliveryLocation: LiveData<LatLng> = _deliveryLocation

    init {
        loadDeliveries()
    }

    private fun loadDeliveries() {
        viewModelScope.launch {
            authRepository.getCurrentUser()?.let { user ->
                deliveryRepository.getDeliveries(user.uid).collect { deliveries ->
                    _deliveries.value = deliveries
                }
            }
        }
    }

    fun trackDelivery(deliveryId: String) {
        viewModelScope.launch {
            deliveryRepository.getDeliveryLocation(deliveryId).collect { location ->
                _deliveryLocation.value = location
            }
        }
    }
}