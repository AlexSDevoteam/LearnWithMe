package learn.with.me.auth.data.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import learn.with.me.auth.domain.auth.AuthRepository

const val LOG_TAG = "AuthRepositoryImpl"

class AuthRepositoryImpl : AuthRepository {
    private val auth by lazy { Firebase.auth }

    override val isUserLoggedIn: Boolean
        get() = auth.currentUser != null


    override suspend fun signInWithEmailAndPassword(email: String, password: String) {

        val authResult = auth.signInWithEmailAndPassword(email, password)
        println("$LOG_TAG User ${authResult.user}")
        println("$LOG_TAG Credential ${authResult.credential}")
        println("$LOG_TAG AdditionalUserInfo ${authResult.additionalUserInfo}")
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ) {
        println("Creating user with email and password")
        val authResult = auth.createUserWithEmailAndPassword(email, password)
        println("$LOG_TAG User ${authResult.user?.email}")
        println("$LOG_TAG User ${authResult.user?.uid}")
        println("$LOG_TAG AdditionalUserInfo ${authResult.additionalUserInfo?.isNewUser}")
        println("$LOG_TAG AdditionalUserInfo ${authResult.additionalUserInfo?.username}")
        println("$LOG_TAG AdditionalUserInfo ${authResult.additionalUserInfo?.providerId}")
        withContext(Dispatchers.IO) {
            authResult.user?.updateProfile(displayName = email.split("@")[0])
        }
        println("$LOG_TAG User ${authResult.user?.displayName}")
        println("$LOG_TAG Credential ${authResult.credential}")
    }

    override suspend fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}
