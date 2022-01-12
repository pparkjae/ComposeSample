package com.example.githubsample.data.service

import com.example.githubsample.data.dto.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("search/users")
    suspend fun getUser(
        @Query("q") name: String,
        @Query("page") page: Int,
        @Query("per_page") itemCount: Int = 30
    ): User
}