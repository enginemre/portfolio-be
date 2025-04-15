package com.engin.domain.usecase

import com.engin.domain.entity.Contact
import com.engin.domain.repository.ContactRepository

class SendContactMessageUseCase(
    private val contactRepository: ContactRepository
) {
    suspend operator fun invoke(contact: Contact): Result<Unit> {
        return try {
            contactRepository.sendContactMessage(contact)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 