package com.example.real_timedeliverytrackingapp.viewModels



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.real_timedeliverytrackingapp.data_model.User
import com.example.real_timedeliverytrackingapp.repository.AuthRepository
import com.example.real_timedeliverytrackingapp.repository.UserRepository

import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _profileState = MutableLiveData<ProfileState>()
    val profileState: LiveData<ProfileState> = _profileState

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            authRepository.getCurrentUser()?.let { firebaseUser ->
                userRepository.getUser(firebaseUser.uid).collect { user ->
                    _user.value = user
                }
            }
        }
    }

    fun updateProfile(name: String, phone: String) {
        viewModelScope.launch {
            _profileState.value = ProfileState.LOADING
            try {
                authRepository.getCurrentUser()?.let { user ->
                    userRepository.updateProfile(user.uid, name, phone)
                    _profileState.value = ProfileState.SUCCESS
                }
            } catch (e: Exception) {
                _profileState.value = ProfileState.ERROR
            }
        }
    }
}

enum class ProfileState {
    LOADING, SUCCESS, ERROR
}