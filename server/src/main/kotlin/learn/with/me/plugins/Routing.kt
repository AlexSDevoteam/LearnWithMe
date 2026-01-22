package learn.with.me.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import learn.with.me.routes.getAllLessons
import learn.with.me.routes.getQuestionsForLesson
import learn.with.me.routes.root

fun Application.configureRouting() {
    routing {
        root()
        getAllLessons()
        getQuestionsForLesson()
    }
}