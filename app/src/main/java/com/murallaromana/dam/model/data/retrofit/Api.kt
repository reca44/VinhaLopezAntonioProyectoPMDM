package com.murallaromana.dam.model.data.retrofit

import com.murallaromana.dam.model.entities.Pelicula
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("movies")
    fun getMovies(): Call<List<Pelicula>>


}