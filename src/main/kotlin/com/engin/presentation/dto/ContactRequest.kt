package com.engin.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class ContactRequest(
    val name: String,
    val surname: String,
    val message: String
) 