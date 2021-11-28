package com.murallaromana.dam

import android.app.Application
import com.murallaromana.dam.model.data.PeliculasDaoMockImpl
import com.murallaromana.dam.model.entities.Pelicula

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