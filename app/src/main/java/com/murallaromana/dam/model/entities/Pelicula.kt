package com.murallaromana.dam.model.entities

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class Pelicula(
    var id:String?,
    @SerializedName("rating")  var puntuacion: String,
    @SerializedName("directorFullname") var director: String,
    @SerializedName("title") var titulo: String,
    @SerializedName("genre") var genero: String,
    @SerializedName("imageUrl") var url: String,
    @SerializedName("runtimeMinutes") var minutos:Int
) : Serializable
