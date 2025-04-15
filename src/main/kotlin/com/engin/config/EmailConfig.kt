package com.engin.config

object EmailConfig {
    val smtpConfig = SmtpConfig(
        host = System.getenv("SMTP_HOST") ?: "smtp.gmail.com",
        port = System.getenv("SMTP_PORT")?.toIntOrNull() ?: 587,
        username = System.getenv("SMTP_USERNAME") ?: error("SMTP username not found!"),
        password = System.getenv("SMTP_PASSWORD") ?: error("SMTP password not found!"),
        from = System.getenv("SMTP_FROM") ?: error("SMTP from address not found!"),
        to = System.getenv("SMTP_TO") ?: error("SMTP to address not found!")
    )
}

data class SmtpConfig(
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val from: String,
    val to: String
) 