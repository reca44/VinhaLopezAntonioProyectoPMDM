package com.murallaromana.dam.model.entities

import java.io.Serializable

class Pelicula(
    var id: Long,
    var nombreComun: String,
    var titulo: String,
    var genero: String,
    var url: String
) : Serializable
