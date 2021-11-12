package com.murallaromana.dam.model.entities.data


import com.murallaromana.dam.model.entities.Pelicula


interface PeliculasDao {

    fun getTodos(): List<Pelicula>


}