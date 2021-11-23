package com.murallaromana.dam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.murallaromana.dam.R
import com.murallaromana.dam.databinding.ActivityDetallesBinding
import com.murallaromana.dam.model.entities.Pelicula
import com.squareup.picasso.Picasso

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    companion object{
        lateinit var infoPelicula:Pelicula
        lateinit var itemGuardar:MenuItem
        lateinit var itemEditar:MenuItem
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        infoPelicula=intent.extras?.get("pelicula") as Pelicula
        if (infoPelicula != null){
            setTitle(infoPelicula.titulo)
            binding.tGenero.setText(infoPelicula.genero)
            binding.tNombreComun.setText(infoPelicula.nombreComun)
            binding.tvTituloDetalle.setText(infoPelicula.titulo)
            binding.tvUrl.setText(infoPelicula.url)
            binding.tNota.setText(infoPelicula.puntuacion.toString())
            Picasso.get().load(infoPelicula.url).into(binding.tvImagen)
        }
        else setTitle("nueva pelicula")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        itemGuardar= menu?.findItem(R.id.action_guardar)!!
        itemEditar= menu?.findItem(R.id.action_editar)!!
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

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Editar Pelicula")
                .setMessage("¿Deseas editar la pelicula "+infoPelicula.titulo+"?.")
                .setPositiveButton("Aceptar", null)
                .setNegativeButton("Cancelar", null)
                .create()
            itemGuardar.isVisible = true
            itemEditar.isVisible = false
            dialog.show()


            return true
        } else if (item.itemId == R.id.action_borrar) {
            //hago otra cosa
            Toast.makeText(this, "Pelicula borrada", Toast.LENGTH_SHORT).show()
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Eliminar pelicula")
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