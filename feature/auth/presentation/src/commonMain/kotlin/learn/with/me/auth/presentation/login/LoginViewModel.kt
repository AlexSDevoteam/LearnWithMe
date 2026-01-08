package learn.with.me.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.with.me.auth.domain.auth.AuthRepository

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signInWithEmailAndPassword(email, password)
        }
    }
}