package learn.with.me.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import learn.with.me.Constants.Auth.EMAIL_REGEX
import learn.with.me.Constants.Auth.PASSWORD_REGEX
import learn.with.me.auth.domain.auth.AuthRepository

class SharedAuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun onEmailTextChange(email: String) {
        _email.value = email
    }

    fun onPasswordTextChange(password: String) {
        _password.value = password
    }

    fun isEmailValid(): Boolean {
        return _email.value.matches(EMAIL_REGEX.toRegex())
    }

    fun isPasswordValid(): Boolean {
        return _password.value.matches(PASSWORD_REGEX.toRegex())
    }

    fun areCredentialsValid(): Boolean {
        return isEmailValid() && isPasswordValid()
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

}
