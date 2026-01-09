package learn.with.me.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val valuesList: List<Lesson> = emptyList(),
    val lastUpdated: Long? = null,
)
