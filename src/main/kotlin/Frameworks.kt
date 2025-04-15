package com.engin

import com.engin.com.engin.di.contactModule
import com.engin.domain.usecase.SendContactMessageUseCase
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

val appModule = module {

}


fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(listOf(appModule, contactModule))
    }
}
