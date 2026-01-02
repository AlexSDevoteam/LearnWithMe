package learn.with.me.auth.data.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import learn.with.me.auth.domain.auth.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    private val auth = Firebase.auth
    override suspend fun signInWithEmailAndPassword(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ) {

        auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}