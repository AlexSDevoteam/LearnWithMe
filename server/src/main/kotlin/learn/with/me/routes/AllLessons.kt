package learn.with.me.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import learn.with.me.repository.LessonRepository
import org.koin.ktor.ext.inject

fun Route.getAllLessons() {
    val lessonRepository: LessonRepository by application.inject()

    get("/lessons") {
        val response = lessonRepository.getAllLessons()
        call.respond(message = response, status = HttpStatusCode.OK)
    }
}