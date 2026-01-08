package learn.with.me.auth.data.user

data class FirebaseUser(
    val uid: String,
    val displayName: String?,
    val email: String?,
    val phoneNumber: String?,
    val photoURL: String?,
    val isAnonymous: Boolean,
    val isEmailVerified: Boolean
)