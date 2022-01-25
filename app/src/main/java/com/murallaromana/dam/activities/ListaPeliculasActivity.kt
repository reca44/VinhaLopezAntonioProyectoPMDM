package com.murallaromana.dam.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.adapters.ListaPeliculasAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import com.murallaromana.dam.App.Companion.peliculas
import com.murallaromana.dam.RetrofitClient
import com.murallaromana.dam.databinding.ActivityListaPeliculasBinding
import com.murallaromana.dam.model.entities.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaPeliculasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaPeliculasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //obtenemos datos

        val listaPeliculas = peliculas
    //creamos componentes
        val layoutManager = LinearLayoutManager(this)
        val adapter = ListaPeliculasAdapter(listaPeliculas,this)

        //asociamos el recyclesview con sus componentes
        binding.rvListaPeliculas.adapter=adapter
        binding.rvListaPeliculas.layoutManager=layoutManager
        binding.rvListaPeliculas.setHasFixedSize(true)

        val divider = DividerItemDecoration(binding.rvListaPeliculas.context, layoutManager.orientation)
        binding.rvListaPeliculas.addItemDecoration(divider)

        val context=this;
        val llamadaApi: Call<List<Pelicula>> = RetrofitClient.apiRetrofit.getMovies()
        llamadaApi.enqueue(object : Callback<List<Pelicula>>{
            override fun onResponse(call: Call<List<Pelicula>>, response: Response<List<Pelicula>>) {
                Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("prueba",t.message.toString())
            }

        })

        binding.fBoton.setOnClickListener{
            val intent = Intent(binding.root.context, DetallesActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        val adapter=ListaPeliculasAdapter(peliculas , this)
        binding.rvListaPeliculas.adapter=adapter
    }
}