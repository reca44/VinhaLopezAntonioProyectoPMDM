package com.murallaromana.dam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.murallaromana.dam.R
import com.murallaromana.dam.model.entities.Pelicula

class detallesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        val infoPelicula=intent.extras?.get("pelicula") as Pelicula
        //infoPelicula.titulo

    }
}