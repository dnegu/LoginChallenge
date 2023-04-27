package com.dnegu.loginpruebatecnica.domain.usecase

import com.dnegu.loginpruebatecnica.data.model.request.NewUserRequest
import com.dnegu.loginpruebatecnica.data.model.response.NewUserResponse
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.domain.repository.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun loginUser(newUserRequest: NewUserRequest): Result<NewUserResponse> {
        return userRepository.loginUser(newUserRequest)
    }
}

