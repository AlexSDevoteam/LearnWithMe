package learn.with.me.model

import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    val id: Int,
    val text: String,
    val questionId: List<Int>,
)
