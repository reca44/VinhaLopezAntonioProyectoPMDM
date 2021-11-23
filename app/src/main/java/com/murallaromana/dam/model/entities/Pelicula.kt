package com.murallaromana.dam.model.entities

import java.io.Serializable

class Pelicula(
    var puntuacion: Double,
    var nombreComun: String,
    var titulo: String,
    var genero: String,
    var url: String
) : Serializable
