package com.dnegu.loginpruebatecnica.domain.usecase

import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUsersListUseCase @Inject constructor(private val recipesRepository: UserRepository) {

    suspend fun getAllUsersList(): Result<List<User>> {
        return recipesRepository.getAllUsersList()
    }
}