package com.dnegu.loginpruebatecnica.domain.usecase

import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getUserDetail(id: Int): Result<User> {
        return userRepository.getUserDetail(id)
    }
}

