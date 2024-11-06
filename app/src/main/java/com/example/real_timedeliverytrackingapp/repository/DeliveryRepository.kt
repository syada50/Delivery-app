package com.example.real_timedeliverytrackingapp.repository



import com.example.real_timedeliverytrackingapp.data_model.Delivery
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
class DeliveryRepository
@Inject constructor(private val firestore: FirebaseFirestore) {
    fun getDeliveries(userId: String) = callbackFlow {
        val listenerRegistration = firestore.collection("deliveries")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val deliveries = snapshot?.toObjects(Delivery::class.java) ?: emptyList()
                trySend(deliveries)
            }
        awaitClose { listenerRegistration.remove() }
    }

    fun getDeliveryLocation(deliveryId: String) = callbackFlow {
        val listenerRegistration = firestore.collection("deliveries")
            .document(deliveryId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val delivery = snapshot?.toObject(Delivery::class.java)
                delivery?.currentLocation?.let { location ->
                    trySend(LatLng(location.latitude, location.longitude))
                }
            }
        awaitClose { listenerRegistration.remove() }
    }
}