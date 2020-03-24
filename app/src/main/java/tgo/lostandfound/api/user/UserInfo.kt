package tgo.lostandfound.api.user

import com.squareup.moshi.Json

class UserInfo(
    @field:Json(name = "name")
    val name: String?,

    @field:Json(name = "bio")
    val bio: String?,

    @field:Json(name = "followers")
    val followers: Int,

    @field:Json(name = "blog")
    val blog: String
)