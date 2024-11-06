package com.example.real_timedeliverytrackingapp.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class AuthRepository(private val auth: FirebaseAuth) {

    fun signIn(email: String, password: String): AuthResult? {
        return try {
            // Perform synchronous sign-in
            val authResult = auth.signInWithEmailAndPassword(email, password).result
            authResult // Return the AuthResult
        } catch (e: Exception) {
            // Handle any exceptions that occur
            null
        }
    }

    fun signUp(email: String, password: String): AuthResult? {
        return try {
            // Perform synchronous sign-up
            val authResult = auth.createUserWithEmailAndPassword(email, password).result
            authResult // Return the AuthResult
        } catch (e: Exception) {
            // Handle any exceptions that occur
            null
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun getCurrentUser() = auth.currentUser
}
