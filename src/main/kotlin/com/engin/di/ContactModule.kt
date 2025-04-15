package com.engin.com.engin.di

import com.engin.data.repository.ContactRepositoryImpl
import com.engin.domain.repository.ContactRepository
import com.engin.domain.usecase.SendContactMessageUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val contactModule = module {
    singleOf<ContactRepository>(::ContactRepositoryImpl)
    singleOf(::SendContactMessageUseCase)
}