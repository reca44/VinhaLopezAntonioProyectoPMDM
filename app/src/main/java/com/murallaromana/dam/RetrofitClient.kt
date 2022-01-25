package com.murallaromana.dam

import com.murallaromana.dam.model.data.retrofit.Api
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RetrofitClient {


    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl("https://damapi.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    val apiRetrofit = getRetrofit().create(Api::class.java)
}

/*    @Synchronized
    fun getInstance(): RetrofitClient? {
        if (instance == null) {
            instance = ClienteRetrofit()
        }
        return instance
    }

    fun getMyApi(): Api? {
        return myApi
    }*/
