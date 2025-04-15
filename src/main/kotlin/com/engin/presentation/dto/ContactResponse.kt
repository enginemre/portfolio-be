package com.engin.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class ContactResponse(
    val status: String,
    val message: String
) 