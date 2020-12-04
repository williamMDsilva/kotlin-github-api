package com.example.githubapikotlin.user

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    var id : Int,
    @SerializedName("login")
    var login : String,
    @SerializedName("node_id")
    var nodeId : String,
    @SerializedName("avatar_url")
    var avatarUrl : String,
)
