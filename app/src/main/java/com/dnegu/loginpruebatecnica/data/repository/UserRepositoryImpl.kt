package com.dnegu.loginpruebatecnica.data.repository

import com.dnegu.loginpruebatecnica.data.model.request.NewUserRequest
import com.dnegu.loginpruebatecnica.data.model.response.NewUserResponse
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.data.service.LoginService
import com.dnegu.loginpruebatecnica.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val recipesService: LoginService
) : UserRepository {

    private var cachedList: MutableList<User> = mutableListOf()

    override suspend fun getUsersList(page: Int): Result<List<User>> {
        val response = recipesService.getUserList(page)
        return if (response.isSuccessful) {
            Result.success(response.body()?.data ?: emptyList())
        } else {
            Result.failure(Throwable("ERROR SERVICE"))
        }
    }

    //TODO Esta funcion está mal, se implemento porque no había una función para buscar por email
    override suspend fun getAllUsersList(): Result<List<User>> {
        cachedList = mutableListOf()
        val response = recipesService.getUserList(1)
        return if (response.isSuccessful) {
            val apiResponse = response.body()
            val recipesList = apiResponse?.data ?: emptyList()
            apiResponse?.let {
                cachedList.addAll(recipesList)
                var page = it.page+1
                while (page <= it.total_pages) {
                    val newResponse = recipesService.getUserList(page).body()
                    val newList = newResponse?.data ?: emptyList()
                    newResponse?.let {
                        page = newResponse.page+1
                        cachedList.addAll(newList)
                    }
                }
            }
            return Result.success(cachedList)
        } else {
            Result.failure(Throwable("ERROR SERVICE"))
        }
    }

    override suspend fun getUserDetail(id: Int): Result<User> {
        val apiResponse = recipesService.getUserById(id)
        return if (apiResponse.isSuccessful) {
            var user = User("", "", "", 0, "")
            apiResponse.body()?.let {
                user = it.data
            }
            Result.success(user)
        } else {
            Result.failure(Throwable("ERROR SERVICE"))
        }
    }

    override suspend fun createUser(newUserRequest: NewUserRequest): Result<NewUserResponse> {
        val response = recipesService.createUser(newUserRequest)
        return if (response.isSuccessful) {
            Result.success(response.body()?: NewUserResponse(0,"") )
        } else {
            Result.failure(Throwable("ERROR SERVICE"))
        }
    }

    override suspend fun loginUser(newUserRequest: NewUserRequest): Result<NewUserResponse> {
        val response = recipesService.loginUser(newUserRequest)
        return if (response.isSuccessful) {
            Result.success(response.body()?: NewUserResponse(0,"") )
        } else {
            Result.failure(Throwable("ERROR SERVICE"))
        }
    }
}