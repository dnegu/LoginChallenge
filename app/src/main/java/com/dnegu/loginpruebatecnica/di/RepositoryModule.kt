package com.dnegu.loginpruebatecnica.di

import com.dnegu.loginpruebatecnica.data.repository.UserRepositoryImpl
import com.dnegu.loginpruebatecnica.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(recipesRepositoryImpl: UserRepositoryImpl): UserRepository
}