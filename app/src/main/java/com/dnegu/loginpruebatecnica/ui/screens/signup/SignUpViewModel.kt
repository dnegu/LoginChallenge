package com.dnegu.loginpruebatecnica.ui.screens.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnegu.loginpruebatecnica.data.model.request.NewUserRequest
import com.dnegu.loginpruebatecnica.data.model.response.NewUserResponse
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.domain.usecase.GetAllUsersListUseCase
import com.dnegu.loginpruebatecnica.domain.usecase.GetUserDetailUseCase
import com.dnegu.loginpruebatecnica.domain.usecase.RegisterUserUseCase
import com.dnegu.loginpruebatecnica.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: RegisterUserUseCase
) : BaseViewModel()  {
    private val _user = MutableLiveData<NewUserResponse>()
    val user: LiveData<NewUserResponse> = _user

    fun registerUser(email: String, password : String){
        call({
            val newUserRequest = NewUserRequest(email, password)
            useCase.registerUser(newUserRequest)
        }, onSuccess = {
            _user.postValue(it)
        }, onError = {
            _user.postValue(NewUserResponse(0,""))
            error.postValue(it.message)
        })
    }
}