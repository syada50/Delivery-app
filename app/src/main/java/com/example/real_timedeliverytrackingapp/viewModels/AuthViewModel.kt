package com.example.real_timedeliverytrackingapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.real_timedeliverytrackingapp.repository.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun signIn(email: String, password: String) {
        try {
            // Perform the sign-in synchronously
            val authResult = authRepository.signIn(email, password)
            if (authResult != null) {
                _authState.value = AuthState.AUTHENTICATED
            } else {
                _authState.value = AuthState.ERROR
            }
        } catch (e: Exception) {
            _authState.value = AuthState.ERROR
        }
    }

    fun signUp(email: String, password: String) {
        try {
            // Perform the sign-up synchronously
            val authResult = authRepository.signUp(email, password)
            if (authResult != null) {
                _authState.value = AuthState.AUTHENTICATED
            } else {
                _authState.value = AuthState.ERROR
            }
        } catch (e: Exception) {
            _authState.value = AuthState.ERROR
        }
    }

    fun signOut() {
        // Sign out the user synchronously
        authRepository.signOut()
        _authState.value = AuthState.UNAUTHENTICATED
    }
}

enum class AuthState {
    AUTHENTICATED, UNAUTHENTICATED, ERROR
}

