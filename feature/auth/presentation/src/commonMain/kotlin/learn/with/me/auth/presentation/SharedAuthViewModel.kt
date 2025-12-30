package learn.with.me.auth.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedAuthViewModel : ViewModel() {
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
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z0-9.-]+\$"

        return _email.value.matches(emailRegex.toRegex())
    }

    /**
     * - `^`                        - Start of values anchor.
     * - `(?=.*[a-z])`              - Ensure at least one lowercase letter.
     * - `(?=.*[A-Z])`              - Ensure at least one uppercase letter.
     * - `(?=.*\\d)`                - Ensure at least one digit.
     * - `(?=.*[@$!%*?&])`          - Ensure at least one special character from the specified set.
     * - `[A-Za-z\\d@$!%*?&]{8,}`   - The password must contain 8 or more characters from the allowed set.
     * - `$                         - End of values anchor.
     */
    fun isPasswordValid(): Boolean {
        val passwordRegex = Regex(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$"
        )

        return _password.value.matches(passwordRegex)
    }


    fun canLogin(): Boolean {
        return isEmailValid() && isPasswordValid()
    }
}
