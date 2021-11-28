package com.murallaromana.dam.model.data


import com.murallaromana.dam.model.entities.Pelicula


interface PeliculasDao {

    fun getTodos(): ArrayList<Pelicula>


}