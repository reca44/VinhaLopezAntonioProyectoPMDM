package com.murallaromana.dam.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.adapters.ListaPeliculasAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import com.murallaromana.dam.App.Companion.peliculas
import com.murallaromana.dam.R
import com.murallaromana.dam.RetrofitClient
import com.murallaromana.dam.activities.LoginActivity.Companion.preferences
import com.murallaromana.dam.databinding.ActivityListaPeliculasBinding
import com.murallaromana.dam.model.data.SharePreferences
import com.murallaromana.dam.model.entities.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.prefs.Preferences


class ListaPeliculasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaPeliculasBinding

    companion object {

        var itemSalir: MenuItem? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //obtenemos datos

        //val listaPeliculas = peliculas
        //creamos componentes

        //val adapter = ListaPeliculasAdapter(listaPeliculas,this)

        //asociamos el recyclesview con sus componentes

        binding.fBoton.setOnClickListener {
            val intent = Intent(binding.root.context, DetallesActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        val preferences = SharePreferences(applicationContext)

        val context = this;
        val token = preferences.llamarToken("token")
        val llamadaApi = RetrofitClient.apiRetrofit.getMovies("Bearer ${token}")
        llamadaApi.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>,
            ) {
                val listaPeliculas = response.body()
                val adapter =
                    listaPeliculas?.let { ListaPeliculasAdapter(it, this@ListaPeliculasActivity) }

                if (response.code() > 299 || response.code() < 200) {

                    if (response.code() == 401 || response.code() == 500) {
                        preferences.cerrarSesion(context)
                    }
                    Toast.makeText(context,
                        "No ha sido posible cargar las Peliculas",
                        Toast.LENGTH_SHORT).show()
                } else {
                    binding.rvListaPeliculas.adapter = adapter
                    val layoutManager = LinearLayoutManager(context)
                    binding.rvListaPeliculas.layoutManager = layoutManager
                    binding.rvListaPeliculas.setHasFixedSize(true)
                    val divider = DividerItemDecoration(binding.rvListaPeliculas.context,
                        layoutManager.orientation)
                    binding.rvListaPeliculas.addItemDecoration(divider)
                }
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("prueba", t.message.toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_listado_peliculas, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //return when (item.itemId) {
        //  R.id.action_salir ->
        if (item.itemId == R.id.action_salir) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Cerrar Sesión")
                .setMessage("Estas a punto de Cerrar sesión ")
                .setPositiveButton("Aceptar") { _, _ ->
                    preferences.cerrarSesion(this)
                }
                .setNegativeButton("Cancelar", null).create()
            dialog.show()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}