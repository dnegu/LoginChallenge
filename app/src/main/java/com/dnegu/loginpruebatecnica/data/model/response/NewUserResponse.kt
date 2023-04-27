package com.dnegu.loginpruebatecnica.data.model.response

data class NewUserResponse(
    val id: Int,
    val token: String
) {
    companion object {
        fun mock() = NewUserResponse(
            id = 7,
            token = "QpwL5tke4Pnpja7X4"
        )
    }
}