package com.engin.presentation.routing

import com.engin.domain.entity.Contact
import com.engin.domain.usecase.SendContactMessageUseCase
import com.engin.presentation.dto.ContactRequest
import com.engin.presentation.dto.ContactResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.contactRoutes() {
    val sendContactMessageUseCase by inject<SendContactMessageUseCase>()
    routing {
        post("/api/contact") {
            try {
                val request = call.receive<ContactRequest>()
                val contact = Contact(
                    name = request.name,
                    surname = request.surname,
                    message = request.message
                )

                sendContactMessageUseCase(contact).fold(
                    onSuccess = {
                        call.respond(
                            HttpStatusCode.OK,
                            ContactResponse("Ok", "Success")
                        )
                    },
                    onFailure = { error ->
                        call.respond(
                            HttpStatusCode.BadRequest,
                            ContactResponse("Error", error.message ?: "An error occurred")
                        )
                    }
                )
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ContactResponse("Error", e.message ?: "Invalid request format")
                )
            }
        }
    }
}
