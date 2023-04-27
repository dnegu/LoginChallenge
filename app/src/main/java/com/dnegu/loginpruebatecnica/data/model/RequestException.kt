package com.dnegu.loginpruebatecnica.data.model

class RequestException(val code: Int, message: String) : Throwable(message)