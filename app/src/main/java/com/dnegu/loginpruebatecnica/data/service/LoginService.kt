package com.dnegu.loginpruebatecnica.data.service

import com.dnegu.loginpruebatecnica.data.model.request.NewUserRequest
import com.dnegu.loginpruebatecnica.data.model.response.NewUserResponse
import com.dnegu.loginpruebatecnica.data.model.response.UserListResponse
import com.dnegu.loginpruebatecnica.data.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface LoginService {
    @GET("users")
    suspend fun getUserList(@Query("page") page: Int): Response<UserListResponse>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserResponse>

    @POST("register")
    suspend fun createUser(@Body newUserRequest: NewUserRequest): Response<NewUserResponse>

    @POST("login")
    suspend fun loginUser(@Body newUserRequest: NewUserRequest): Response<NewUserResponse>
}