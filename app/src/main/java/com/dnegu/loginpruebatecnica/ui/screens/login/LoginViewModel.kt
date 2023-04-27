package com.dnegu.loginpruebatecnica.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnegu.loginpruebatecnica.data.model.request.NewUserRequest
import com.dnegu.loginpruebatecnica.data.model.response.NewUserResponse
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.domain.usecase.GetAllUsersListUseCase
import com.dnegu.loginpruebatecnica.domain.usecase.GetUserDetailUseCase
import com.dnegu.loginpruebatecnica.domain.usecase.LoginUserUseCase
import com.dnegu.loginpruebatecnica.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDetailUseCase: GetUserDetailUseCase,
    private val loginUseCase: LoginUserUseCase
) : BaseViewModel()  {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _loginUser = MutableLiveData<NewUserResponse>()
    val loginUser: LiveData<NewUserResponse> = _loginUser

    fun getUserById(id: Int){
        call({
            userDetailUseCase.getUserDetail(id)
        }, onSuccess = {
            _user.postValue(it)
        }, onError = {
            error.postValue(it.message)
        })
    }

    fun loginUser(email: String, password : String){
        call({
            val newUserRequest = NewUserRequest(email, password)
            loginUseCase.loginUser(newUserRequest)
        }, onSuccess = {
            _loginUser.postValue(it)
        }, onError = {
            error.postValue(it.message)
        })
    }
}