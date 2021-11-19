package com.murallaromana.dam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.murallaromana.dam.R
import com.murallaromana.dam.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.databinding.ActivityDetallesBinding

import com.murallaromana.dam.model.entities.Pelicula
import com.squareup.picasso.Picasso

class detallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    private lateinit var infoPelicula:Pelicula


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        infoPelicula=intent.extras?.get("pelicula") as Pelicula
        binding.tGenero.setText(infoPelicula.genero)
        binding.tNombreComun.setText(infoPelicula.nombreComun)
        binding.tvTituloDetalle.setText(infoPelicula.titulo)
        binding.tNota.setText(infoPelicula.puntuacion.toString())
        Picasso.get().load(infoPelicula.url).into(binding.tvImagen)
        if (infoPelicula != null) setTitle(infoPelicula.titulo)
        else setTitle("nueva pelicula")


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_guardar) {
            //hago algo
            Toast.makeText(this, "Pelicula guardada", Toast.LENGTH_SHORT).show()

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Usuario guardado")
                .setMessage("estas a punto de guardar la pelicula "+infoPelicula.titulo+"¿Estas seguro?.")
                .setPositiveButton("Aceptar", null)
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()

            return true
        } else if (item.itemId == R.id.action_borrar) {
            //hago otra cosa
            Toast.makeText(this, "Pelicula borrada", Toast.LENGTH_SHORT).show()
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Usuario eliminado")
                .setMessage("Estas a punto de eliminar la pelicula "+infoPelicula.titulo+". ¿Estas seguro?.")
                .setPositiveButton("Aceptar", { dialog, id -> finish() })
                .setNegativeButton("Cancelar", null).create()
            dialog.show()


            return true
        } else {
            return super.onOptionsItemSelected(item)
        }


    }
}