package learn.with.me.model

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int,
    val text: String,
    val answers: List<Answer>,
    val moduleId: List<Int>,
    val correctAnswerId: Int,
)
