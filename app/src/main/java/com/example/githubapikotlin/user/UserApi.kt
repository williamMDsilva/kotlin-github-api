package com.example.githubapikotlin.user

import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("users?since=5")
    fun getUsers(): Call<List<User>>
}