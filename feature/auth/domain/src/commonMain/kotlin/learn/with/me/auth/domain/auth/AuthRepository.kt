package learn.with.me.auth.domain.auth

interface AuthRepository {
    val isUserLoggedIn: Boolean

    suspend fun signInWithEmailAndPassword(email: String, password: String)
    suspend fun createUserWithEmailAndPassword(email: String, password: String)
    suspend fun sendPasswordResetEmail(email: String)
    suspend fun signOut()
}
