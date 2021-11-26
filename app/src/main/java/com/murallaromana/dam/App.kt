package com.murallaromana.dam

import android.app.Application
import android.util.Log
import com.murallaromana.dam.model.data.PeliculasDaoMockImpl
import com.murallaromana.dam.model.entities.Pelicula
import kotlin.math.log

class App : Application() {

    companion object {
        var peliculas: ArrayList<Pelicula> = ArrayList()
    }

    override fun onCreate() {
        super.onCreate()

        val dao = PeliculasDaoMockImpl()
        peliculas = dao.getTodos()

    }
}