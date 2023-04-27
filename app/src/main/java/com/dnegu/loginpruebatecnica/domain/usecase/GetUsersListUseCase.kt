package com.dnegu.loginpruebatecnica.domain.usecase

import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(private val recipesRepository: UserRepository) {

    suspend fun getUsersList(page: Int): Result<List<User>> {
        return recipesRepository.getUsersList(page)
    }
}