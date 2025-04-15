package com.engin.domain.repository

import com.engin.domain.entity.Contact

interface ContactRepository {
    suspend fun sendContactMessage(contact: Contact)
} 