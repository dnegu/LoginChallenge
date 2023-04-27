package com.dnegu.loginpruebatecnica.data.model.response

import com.dnegu.loginpruebatecnica.domain.model.Support
import com.dnegu.loginpruebatecnica.domain.model.User

data class UserListResponse(
    val data: List<User>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
){
    companion object {
        fun mock() = UserListResponse(
            data = listOf(
                User(
                    id = 7,
                    email = "michael.lawson@reqres.in",
                    first_name = "Michael",
                    last_name = "Lawson",
                    avatar = "https://reqres.in/img/faces/7-image.jpg"
                ),
                User(
                    id = 8,
                    email = "lindsay.ferguson@reqres.in",
                    first_name = "Lindsay",
                    last_name = "Ferguson",
                    avatar = "https://reqres.in/img/faces/8-image.jpg"
                ),
                User(
                    id = 9,
                    email = "tobias.funke@reqres.in",
                    first_name = "Tobias",
                    last_name = "Funke",
                    avatar = "https://reqres.in/img/faces/9-image.jpg"
                ),
                User(
                    id = 10,
                    email = "byron.fields@reqres.in",
                    first_name = "Byron",
                    last_name = "Fields",
                    avatar = "https://reqres.in/img/faces/10-image.jpg"
                ),
                User(
                    id = 11,
                    email = "george.edwards@reqres.in",
                    first_name = "George",
                    last_name = "Edwards",
                    avatar = "https://reqres.in/img/faces/11-image.jpg"
                ),
                User(
                    id = 12,
                    email = "rachel.howell@reqres.in",
                    first_name = "Rachel",
                    last_name = "Howell",
                    avatar = "https://reqres.in/img/faces/12-image.jpg"
                )
            ),
            page = 1,
            per_page = 6,
            support = Support(
                url = "https://reqres.in/#support-heading",
                text ="To keep ReqRes free, contributions towards server costs are appreciated!"
            ),
            total = 12,
            total_pages = 2,
        )
    }
}