package com.example.real_timedeliverytrackingapp.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.real_timedeliverytrackingapp.repository.AuthRepository

import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                authRepository.signIn(email, password)
                _authState.value = AuthState.AUTHENTICATED
            } catch (e: Exception) {
                _authState.value = AuthState.ERROR
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                authRepository.signUp(email, password)
                _authState.value = AuthState.AUTHENTICATED
            } catch (e: Exception) {
                _authState.value = AuthState.ERROR
            }
        }
    }

    fun signOut() {
        authRepository.signOut()
        _authState.value = AuthState.UNAUTHENTICATED
    }
}

enum class AuthState {
    AUTHENTICATED, UNAUTHENTICATED, ERROR
}