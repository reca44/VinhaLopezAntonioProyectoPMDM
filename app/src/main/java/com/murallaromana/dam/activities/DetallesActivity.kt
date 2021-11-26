package com.murallaromana.dam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.murallaromana.dam.R
import com.murallaromana.dam.databinding.ActivityDetallesBinding
import com.murallaromana.dam.App.Companion.peliculas
import com.murallaromana.dam.model.entities.Pelicula
import com.squareup.picasso.Picasso

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    private lateinit var infoPelicula: Pelicula

    companion object {

        var itemGuardar: MenuItem? = null
        var itemEditar: MenuItem? = null
        var itemBorrar: MenuItem? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        if (intent.extras?.get("pelicula") != null) {
            infoPelicula = intent.extras?.get("pelicula") as Pelicula
            setTitle(infoPelicula.titulo)
            binding.tGenero.setText(infoPelicula.genero)
            binding.tNombreComun.setText(infoPelicula.director)
            binding.tvTituloDetalle.setText(infoPelicula.titulo)
            binding.tvUrl.setText(infoPelicula.url)
            binding.tNota.setText(infoPelicula.puntuacion.toString())
            Picasso.get().load(infoPelicula.url).into(binding.tvImagen)
//            itemEditar?.isVisible = false
//            itemGuardar?.isVisible = false
//            itemBorrar?.isVisible=false
        } else {
            setTitle("nueva pelicula")
            binding.tvTituloDetalle.isEnabled = true
            binding.tvUrl.isEnabled = true
            binding.tGenero.isEnabled = true
            binding.tNota.isEnabled = true
            binding.tNombreComun.isEnabled = true
            Picasso.get()
                .load("https://lh3.googleusercontent.com/proxy/QCDDT0GjsBW4UzhfKjEDZr0gQusjFdIXPtBk2RfZpM7cqu5cSGdaJWSgwRbesoM30GhHEFddPNWHAI6NFpNvZ7ZNnL0vLMemGaJHji--5_aZBA7OJNxT1BmlJrkJN4n1")
                .into(binding.tvImagen)

//            itemEditar?.isVisible = false

            Log.d("PRUEBA", "Estoy en onResume")
//            itemGuardar?.isVisible = true
//            itemBorrar?.isVisible=false
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        Log.d("PRUEBA", "Estoy en onCreateOptionsMenu")
        if (menu != null) {
            itemGuardar = menu.findItem(R.id.action_guardar)
            itemBorrar = menu.findItem(R.id.action_borrar)
            itemEditar = menu.findItem(R.id.action_editar)

        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (intent.extras?.get("pelicula") != null) {
            itemEditar?.isVisible = true
            itemGuardar?.isVisible = false
            itemBorrar?.isVisible = true
        } else {
            itemEditar?.isVisible = false
            itemGuardar?.isVisible = true
            itemBorrar?.isVisible = false
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_editar) {

            //cambio los campos a editables
            binding.tvTituloDetalle.isEnabled = true
            binding.tvUrl.isEnabled = true
            binding.tGenero.isEnabled = true
            binding.tNota.isEnabled = true
            binding.tNombreComun.isEnabled = true

            Toast.makeText(this, "Pantalla de edición", Toast.LENGTH_SHORT).show()


            itemGuardar?.isVisible = true
            itemEditar?.isVisible = false

            return true
        } else if (item.itemId == R.id.action_borrar) {
            Toast.makeText(this, "Pelicula borrada", Toast.LENGTH_SHORT).show()
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Eliminar pelicula")
                .setMessage("Estas a punto de eliminar la pelicula " + infoPelicula.titulo + ". ¿Estas seguro?.")
                .setPositiveButton("Aceptar", { _, _ ->
                    peliculas.remove(infoPelicula)
                    Toast.makeText(this, "Película Borrada", Toast.LENGTH_SHORT).show()
                    finish()
                })
                .setNegativeButton("Cancelar", null).create()
            dialog.show()


            return true
        } else if (item.itemId == R.id.action_guardar) {
            setTitle(infoPelicula.titulo)
            infoPelicula.genero = binding.tGenero.text.toString()
//            binding.tNombreComun.setText(infoPelicula.director)
//            binding.tvTituloDetalle.setText(infoPelicula.titulo)
//            binding.tvUrl.setText(infoPelicula.url)
//            binding.tNota.setText(infoPelicula.puntuacion.toString())



            //peliculas.add(infoPelicula)
            Toast.makeText(this, "Película Guardada", Toast.LENGTH_SHORT).show()
            finish()

            return true
        } else {
            return super.onOptionsItemSelected(item)
        }


    }
}