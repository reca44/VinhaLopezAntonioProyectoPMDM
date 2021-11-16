package com.murallaromana.dam.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.databinding.ActivityListaPeliculasBinding
import com.murallaromana.dam.model.data.PeliculasDaoMockImpl
import androidx.recyclerview.widget.DividerItemDecoration





class ListaPeliculasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaPeliculasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_main)

        binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //obtenemos datos
        val peliculasDao = PeliculasDaoMockImpl()
        val listaPeliculas = peliculasDao.getTodos()
    //creamos componentes
        val layoutManager = LinearLayoutManager(this)
        val adapter = ListaPeliculasAdapter(listaPeliculas)

        //asociamos el recyclesview con sus componentes
        binding.rvListaPersonajes.adapter=adapter
        binding.rvListaPersonajes.layoutManager=layoutManager
        binding.rvListaPersonajes.setHasFixedSize(true)

        val divider = DividerItemDecoration(binding.rvListaPersonajes.context, layoutManager.orientation)
        binding.rvListaPersonajes.addItemDecoration(divider)


        binding.fBoton.setOnClickListener{
            val intent = Intent(this, detallesActivity::class.java)
            startActivity(intent)
        }

    }
}