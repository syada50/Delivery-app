package com.example.real_timedeliverytrackingapp.repository



import com.example.real_timedeliverytrackingapp.data_model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
class UserRepository
@Inject constructor(private val firestore: FirebaseFirestore) {
    fun updateProfile(userId: String, name: String, phone: String) = firestore
        .collection("users")
        .document(userId)
        .update(mapOf(
            "name" to name,
            "phone" to phone
        ))

    fun getUser(userId: String) = callbackFlow {
        val listenerRegistration = firestore.collection("users")
            .document(userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val user = snapshot?.toObject(User::class.java)
                if (user != null) {
                    trySend(user)
                }
            }
        awaitClose { listenerRegistration.remove() }
    }
}