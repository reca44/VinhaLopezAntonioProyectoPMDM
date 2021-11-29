package com.murallaromana.dam.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.murallaromana.dam.R
import com.murallaromana.dam.App.Companion.peliculas
import com.murallaromana.dam.databinding.ActivityDetallesBinding
import com.murallaromana.dam.model.entities.Pelicula
import com.squareup.picasso.Picasso
import java.lang.Exception

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    private lateinit var infoPelicula: Pelicula

    companion object {

        var itemGuardar: MenuItem? = null
        var itemEditar: MenuItem? = null
        var itemBorrar: MenuItem? = null
        var intemwhatsapp: MenuItem? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLlamar.setOnClickListener {
            val telefono = "tel:666655589"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(telefono)))
        }
    }

    override fun onResume() {
        super.onResume()

        if (intent.extras?.get("pelicula") != null) {
            infoPelicula = intent.extras?.get("pelicula") as Pelicula
            title = infoPelicula.titulo
            binding.tGenero.setText(infoPelicula.genero)
            binding.tDirector.setText(infoPelicula.director)
            binding.tvTituloDetalle.setText(infoPelicula.titulo)
            binding.tvUrl.setText(infoPelicula.url)
            binding.tNota.setText(infoPelicula.puntuacion)
            Picasso.get().load(infoPelicula.url).into(binding.tvImagen)
        } else {
            title = "nueva pelicula"
            binding.tvTituloDetalle.isEnabled = true
            binding.tvUrl.isEnabled = true
            binding.tGenero.isEnabled = true
            binding.tNota.isEnabled = true
            binding.tDirector.isEnabled = true
            Picasso.get()
                .load("https://png.pngtree.com/png-vector/20190728/ourlarge/pngtree-avatar-user-profile-flat-color-icon-vector-icon-banner-png-image_1619399.jpg")
                .into(binding.tvImagen)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        if (menu != null) {
            itemGuardar = menu.findItem(R.id.action_guardar)
            itemBorrar = menu.findItem(R.id.action_borrar)
            itemEditar = menu.findItem(R.id.action_editar)
            intemwhatsapp = menu.findItem(R.id.action_llamar)

        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (intent.extras?.get("pelicula") != null) {
            itemEditar?.isVisible = true
            itemGuardar?.isVisible = false
            itemBorrar?.isVisible = true
            intemwhatsapp?.isVisible = true
        } else {
            itemEditar?.isVisible = false
            itemGuardar?.isVisible = true
            itemBorrar?.isVisible = false
            intemwhatsapp?.isVisible = false
            binding.btnLlamar.isVisible = false
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_editar -> {
                //cambio los campos a editables
                binding.tvTituloDetalle.isEnabled = true
                binding.tvUrl.isEnabled = true
                binding.tGenero.isEnabled = true
                binding.tNota.isEnabled = true
                binding.tDirector.isEnabled = true
                Toast.makeText(this, "Pantalla de edición", Toast.LENGTH_SHORT).show()
                itemGuardar?.isVisible = true
                itemEditar?.isVisible = false
                itemBorrar?.isVisible = false
                intemwhatsapp?.isVisible = false

                return true
            }
            R.id.action_borrar -> {
                Toast.makeText(this, "Pelicula borrada", Toast.LENGTH_SHORT).show()
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Eliminar pelicula")
                    .setMessage("Estas a punto de eliminar la pelicula " + infoPelicula.titulo + ". ¿Estas seguro?.")
                    .setPositiveButton("Aceptar") { _, _ ->
                        peliculas.remove(infoPelicula)
                        Toast.makeText(this, "Película Borrada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("Cancelar", null).create()
                dialog.show()

                return true
            }
            R.id.action_guardar -> {

                if (binding.tNota.text.toString().isEmpty() || binding.tDirector.text.toString()
                        .isEmpty() || binding.tDirector.text.toString().isEmpty()
                    || binding.tvTituloDetalle.text.toString()
                        .isEmpty() || binding.tGenero.text.toString()
                        .isEmpty() || binding.tvUrl.text.toString().isEmpty()
                ) {
                    if (binding.tvTituloDetalle.text.toString().isEmpty()) {
                        binding.tvTituloDetalle.error = "El campo titulo no puede estar vacio"
                    }

                    if (binding.tNota.text.toString().isEmpty()) {
                        binding.tNota.error = "El campo nota no puede estar vacio"
                    }
                    if (binding.tDirector.text.toString().isEmpty()) {
                        binding.tDirector.error = "El campo Director no puede estar vacio"
                    }
                    if (binding.tGenero.text.toString().isEmpty()) {
                        binding.tGenero.error = "El campo Genero no puede estar vacio"
                    }
                    if (binding.tvUrl.text.toString().isEmpty()) {
                        binding.tvUrl.error = "El campo URL no puede estar vacio"
                    }
                } else if (binding.tNota.text.toString().toDouble() > 10) {
                    binding.tNota.error = "La nota tiene que estar entre 0 y 10"
                } else {
                    val peliculaCreada = Pelicula(
                        binding.tNota.text.toString(),
                        binding.tDirector.text.toString(),
                        binding.tvTituloDetalle.text.toString(),
                        binding.tGenero.text.toString(),
                        binding.tvUrl.text.toString()
                    )
                    if (intent.extras?.get("pelicula") == null) {
                        peliculas.add(peliculaCreada)
                    } else {
                        val indicePeli = peliculas.indexOf(infoPelicula)
                        peliculas[indicePeli] = peliculaCreada
                    }
                    Toast.makeText(this, "Película Guardada", Toast.LENGTH_SHORT).show()
                    finish()


                }
                return true
            }
            R.id.action_llamar -> {
                try {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Te recomiendo que veas la pelicula: " + binding.tvTituloDetalle.text.toString() + "." +
                                "\n Registrate en la App RecaFilms para mas info: \n" + binding.tvUrl.text.toString()
                    )
                    intent.`package` = "com.whatsapp"
                    startActivity(intent)
                } catch (exp: Exception) {
                    Toast.makeText(
                        this,
                        "Whatsapp no está instalado",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}