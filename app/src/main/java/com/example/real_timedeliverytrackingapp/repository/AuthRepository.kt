package com.example.real_timedeliverytrackingapp.repository



import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository
@Inject constructor(private val auth: FirebaseAuth) {
    suspend fun signIn(email: String, password: String): AuthResult = withContext(Dispatchers.IO) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun signUp(email: String, password: String): AuthResult = withContext(Dispatchers.IO) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    fun signOut() = auth.signOut()

    fun getCurrentUser() = auth.currentUser
}