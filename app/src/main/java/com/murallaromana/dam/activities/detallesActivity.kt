package com.murallaromana.dam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murallaromana.dam.R
import com.murallaromana.dam.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.databinding.ItemPeliculaBinding
import com.murallaromana.dam.model.entities.Pelicula

class detallesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val infoPelicula=intent.extras?.get("pelicula") as Pelicula


        if (infoPelicula!=null)setTitle(infoPelicula.titulo)
        else setTitle("nueva pelicula")



    }
}