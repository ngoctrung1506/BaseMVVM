package tgo.lostandfound.api.meta

import com.squareup.moshi.Json

class MetaData(
    @field:Json(name = "hooks")
    var listHooks: List<String>
)