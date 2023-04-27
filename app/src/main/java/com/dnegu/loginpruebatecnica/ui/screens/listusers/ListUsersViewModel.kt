package com.dnegu.loginpruebatecnica.ui.screens.listusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.domain.usecase.GetAllUsersListUseCase
import com.dnegu.loginpruebatecnica.domain.usecase.GetUsersListUseCase
import com.dnegu.loginpruebatecnica.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListUsersViewModel @Inject constructor(
    private val useCase: GetUsersListUseCase
) : BaseViewModel()  {
    private var listUser:List<User> = listOf()

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _userList

    fun getUserList(page: Int){
        call({
            useCase.getUsersList(page)
        }, onSuccess = {
            _userList.postValue(it)
            listUser = it
        }, onError = {
            error.postValue(it.message)
        })
    }

    fun searchWithParam(value: String){
        if(value.isNotEmpty()){
            val listSearch = listUser.filter { recipe ->
                recipe.first_name.contains(value, true) || recipe.last_name.contains(value, true)
            }
            _userList.postValue(listSearch)
        } else {
            _userList.postValue(listUser)
        }
    }
}