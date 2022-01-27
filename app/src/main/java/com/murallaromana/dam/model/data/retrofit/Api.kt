package com.murallaromana.dam.model.data.retrofit

import com.murallaromana.dam.model.entities.Pelicula
import com.murallaromana.dam.model.entities.Token
import com.murallaromana.dam.model.entities.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("movies")
    fun getMovies(): Call<List<Pelicula>>

    @POST("users/signup")
    fun signup(@Body user: Usuario): Call<Unit>

    @POST("users/login")
    fun login(@Body user: Usuario): Call<Token>
}