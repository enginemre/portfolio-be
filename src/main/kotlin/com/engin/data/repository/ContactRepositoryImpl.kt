package com.engin.data.repository

import com.engin.config.EmailConfig
import com.engin.domain.entity.Contact
import com.engin.domain.repository.ContactRepository
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class ContactRepositoryImpl : ContactRepository {
    override suspend fun sendContactMessage(contact: Contact) {
        val props = System.getProperties()
        props["mail.smtp.host"] = EmailConfig.smtpConfig.host
        props["mail.smtp.port"] = EmailConfig.smtpConfig.port.toString()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"

        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(
                    EmailConfig.smtpConfig.username,
                    EmailConfig.smtpConfig.password
                )
            }
        })

        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(EmailConfig.smtpConfig.from))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(EmailConfig.smtpConfig.to))
            message.subject = "Yeni İletişim Formu Mesajı"
            message.setText("""
                Ad: ${contact.name}
                Soyad: ${contact.surname}
                Mesaj: ${contact.message}
            """.trimIndent())

            Transport.send(message)
        } catch (e: MessagingException) {
            throw RuntimeException("Mail gönderilirken hata oluştu: ${e.message}")
        }
    }
} 