package com.murallaromana.dam.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.R
import com.murallaromana.dam.App.Companion.peliculas
import com.murallaromana.dam.RetrofitClient
import com.murallaromana.dam.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.databinding.ActivityDetallesBinding
import com.murallaromana.dam.model.data.SharePreferences
import com.murallaromana.dam.model.entities.Pelicula
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    private var infoPelicula : Pelicula?=null

    companion object {

        var itemGuardar: MenuItem? = null
        var itemEditar: MenuItem? = null
        var itemBorrar: MenuItem? = null
        var intemwhatsapp: MenuItem? = null
        var id: String? = null
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
        if (intent.extras?.get("id") != null) {
            id = intent.extras?.get("id") as String?
            val preferences = SharePreferences(applicationContext)
            val token =preferences.llamarToken("token")
            if (id != null){
                val llamadaApi = RetrofitClient.apiRetrofit.getbyid("Bearer $token", id!!)
                llamadaApi.enqueue(object : Callback<Pelicula> {
                    override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                        infoPelicula=response.body()!!
                        title = infoPelicula?.titulo
                        binding.tGenero.setText(infoPelicula?.genero)
                        binding.tDirector.setText(infoPelicula?.director)
                        binding.tvTituloDetalle.setText(infoPelicula?.titulo)
                        binding.tvUrl.setText(infoPelicula?.url)
                        binding.tNota.setText(infoPelicula?.puntuacion)
                        binding.tMinutos.setText(infoPelicula?.minutos.toString())
                        Picasso.get().load(infoPelicula?.url).into(binding.tvImagen)
                    }

                    override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                        Log.d("Fallo obtener id",t.message.toString())
                    }
                })
            }


        } else {
            title = "nueva pelicula"
            binding.tvTituloDetalle.isEnabled = true
            binding.tvUrl.isEnabled = true
            binding.tGenero.isEnabled = true
            binding.tNota.isEnabled = true
            binding.tDirector.isEnabled = true
            binding.tMinutos.isEnabled=true
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
        if (intent.extras?.get("id") != null) {
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
                binding.tMinutos.isEnabled=true
                Toast.makeText(this, "Pantalla de edición", Toast.LENGTH_SHORT).show()
                itemGuardar?.isVisible = true
                itemEditar?.isVisible = false
                itemBorrar?.isVisible = false
                intemwhatsapp?.isVisible = false

                return true
            }
            R.id.action_borrar -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Eliminar pelicula")
                    .setMessage("Estas a punto de eliminar la pelicula " + infoPelicula?.titulo + ". ¿Estas seguro?.")
                    .setPositiveButton("Aceptar") { _, _ ->
                        val preferences = SharePreferences(applicationContext)
                        val token =preferences.llamarToken("token")
                        val context=this
                        val llamadaApi = RetrofitClient.apiRetrofit.delete("Bearer $token",id)
                        llamadaApi.enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                if (response.isSuccessful){
                                    Toast.makeText(context, "Pelicula borrada", Toast.LENGTH_SHORT).show()
                                    finish()
                                }

                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("Error al borrar",t.message.toString())
                            }
                        })
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
                        .isEmpty() || binding.tvUrl.text.toString().isEmpty()|| binding.tMinutos.text.toString().isEmpty()
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
                    if (binding.tMinutos.text.toString().toInt()<=0) {
                        binding.tMinutos.error = "El campo Minutos no puede ser 0"
                    }
                } else if (binding.tNota.text.toString().toDouble() > 10) {
                    binding.tNota.error = "La nota tiene que estar entre 0 y 10"

                } else {

                    val preferences = SharePreferences(applicationContext)
                    val token =preferences.llamarToken("token")
                    val context=this

                    if(infoPelicula != null){
                        val updatePelicula=Pelicula(infoPelicula?.id,
                                                    binding.tNota.text.toString(),
                                                    binding.tDirector.text.toString(),
                                                    binding.tvTituloDetalle.text.toString(),
                                                    binding.tGenero.text.toString(),
                                                    binding.tvUrl.text.toString(),
                                                    binding.tMinutos.text.toString().toInt())
                    val llamadaApi=RetrofitClient.apiRetrofit.editar("Bearer $token",updatePelicula)
                    llamadaApi.enqueue(object: Callback<Unit>{
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            Toast.makeText(context, "Película Actualizada", Toast.LENGTH_SHORT).show()
                            finish()
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Toast.makeText(context, "No se puede actualizar la pelicula", Toast.LENGTH_SHORT).show()
                        }

                    })
                    }else{
                        val peliculaCreada = Pelicula(
                            null,
                            binding.tNota.text.toString(),
                            binding.tDirector.text.toString(),
                            binding.tvTituloDetalle.text.toString(),
                            binding.tGenero.text.toString(),
                            binding.tvUrl.text.toString(),
                            binding.tMinutos.text.toString().toInt()
                        )
                        val llamadaApi = RetrofitClient.apiRetrofit.create("Bearer $token",peliculaCreada)
                        llamadaApi.enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                Toast.makeText(context, "Película Guardada", Toast.LENGTH_SHORT).show()
                                finish()
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("prueba",t.message.toString())
                            }
                        })
                    }
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