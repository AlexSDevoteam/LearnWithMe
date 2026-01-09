package learn.with.me

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import learn.with.me.Constants.SERVER_PORT
import learn.with.me.plugins.configureKoin
import learn.with.me.plugins.configureRouting

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureKoin()
    configureRouting()
}