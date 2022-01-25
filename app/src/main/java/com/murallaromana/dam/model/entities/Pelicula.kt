package com.murallaromana.dam.model.entities

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class Pelicula(
    var puntuacion: String,
    var director: String,
    @SerializedName("title") var titulo: String,
    var genero: String,
    var url: String,
) : Serializable
