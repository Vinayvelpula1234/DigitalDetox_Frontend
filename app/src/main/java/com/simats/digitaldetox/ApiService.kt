package com.simats.digitaldetox

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse> // Use LoginResponse for both

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("get_profile.php")
    fun getProfile(@Query("user_id") userId: Int): Call<Profile>

    @FormUrlEncoded
    @POST("update_profile.php")
    fun updateProfile(
        @Field("user_id") userId: Int,
        @Field("name") name: String,
        @Field("language") language: String
    ): Call<UpdateProfileResponse>
}
