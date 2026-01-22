package learn.with.me.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import learn.with.me.repository.LessonRepository
import org.koin.ktor.ext.inject

fun Route.getQuestionsForLesson() {
    val lessonRepository: LessonRepository by application.inject<LessonRepository>()

    get("/lessons/{lessonId}/questions") {
        val lessonId = call.parameters["lessonId"]?.toIntOrNull() ?: return@get call.respond(
            message = "Invalid lesson ID",
            status = HttpStatusCode.BadRequest
        )
        val response = lessonRepository.getQuestions(lessonId)
        call.respond(message = response, status = HttpStatusCode.OK)
    }
}