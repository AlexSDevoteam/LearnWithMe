package learn.with.me.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import learn.with.me.auth.domain.auth.AuthRepository

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _confirmPassword: MutableStateFlow<String> = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()


    fun onConfirmPasswordTextChange(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun isConfirmPasswordValid(password: String): Boolean {
        return _confirmPassword.value == password
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                authRepository.createUserWithEmailAndPassword(email, password)
                //Result.Success
            } catch (e: Exception) {
                // TODO change this to send Result.Error
                println("Error creating user ${e.message}")
            }
        }
    }
}

