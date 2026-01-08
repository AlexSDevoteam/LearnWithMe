package learn.with.me.auth.domain.user

data class User(
    val id: String,
    val email: String,
    val name: String?,
    val phone: String?,
    val imageUrl: String?,
)