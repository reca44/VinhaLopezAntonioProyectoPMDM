package com.murallaromana.dam.model.data.retrofit

import com.murallaromana.dam.model.entities.Pelicula
import com.murallaromana.dam.model.entities.Token
import com.murallaromana.dam.model.entities.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @GET("movies")
    fun getMovies(@Header("Authorization") token: String): Call<List<Pelicula>>

    @POST("movies")
    fun delete(@Header("Authorization") token:String ,
               @Body id:String):Call<Unit>

    @POST("movies")
    fun create(@Header("Authorization") token:String ,
               @Body peli:Pelicula):Call<Unit>

    @POST("users/signup")
    fun signup(@Body user: Usuario): Call<Unit>

    @POST("users/login")
    fun login(@Body user: Usuario): Call<Token>
}