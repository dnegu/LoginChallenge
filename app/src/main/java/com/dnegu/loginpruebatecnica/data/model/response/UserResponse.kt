package com.dnegu.loginpruebatecnica.data.model.response

import com.dnegu.loginpruebatecnica.domain.model.Support
import com.dnegu.loginpruebatecnica.domain.model.User

data class UserResponse(
    val data: User,
    val support: Support
) {
    companion object {
        fun mock() = UserResponse(
            data = User(
                id = 7,
                email = "michael.lawson@reqres.in",
                first_name = "Michael",
                last_name = "Lawson",
                avatar = "https://reqres.in/img/faces/7-image.jpg"
            ),
            support = Support(
                url = "https://reqres.in/#support-heading",
                text = "To keep ReqRes free, contributions towards server costs are appreciated!"
            )
        )
    }
}
