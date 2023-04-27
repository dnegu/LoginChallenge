package com.dnegu.loginpruebatecnica.ui.screens.greeting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.domain.usecase.GetAllUsersListUseCase
import com.dnegu.loginpruebatecnica.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val useCase: GetAllUsersListUseCase
) : BaseViewModel()  {
    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _userList

    fun getAllUserList(){
        call({
            useCase.getAllUsersList()
        }, onSuccess = {
            _userList.postValue(it)
        }, onError = {
            error.postValue(it.message)
        })
    }

    fun searchEmailInList(email: String): User{
        userList.value?.find {
            it.email == email
        }?.let {user ->
          return user
        }
        return User("","","",0,"")
    }
}