package com.dnegu.loginpruebatecnica.domain.repository

import com.dnegu.loginpruebatecnica.data.model.request.NewUserRequest
import com.dnegu.loginpruebatecnica.data.model.response.NewUserResponse
import com.dnegu.loginpruebatecnica.domain.model.User

interface UserRepository {
    suspend fun getUsersList(page: Int): Result<List<User>>

    suspend fun getAllUsersList(): Result<List<User>>

    suspend fun getUserDetail(id: Int): Result<User>

    suspend fun createUser(newUserRequest: NewUserRequest): Result<NewUserResponse>

    suspend fun loginUser(newUserRequest: NewUserRequest): Result<NewUserResponse>
}