package learn.with.me.model

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
)
