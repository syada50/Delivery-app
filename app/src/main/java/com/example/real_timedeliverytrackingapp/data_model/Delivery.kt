package com.example.real_timedeliverytrackingapp.data_model

import com.google.firebase.firestore.GeoPoint

data class Delivery(
    val id: String = "",
    val userId: String = "",
    val status: String = "",
    val expectedDeliveryDate: String = "",
    val currentLocation: GeoPoint? = null,
    val destinationLocation: GeoPoint? = null
)
