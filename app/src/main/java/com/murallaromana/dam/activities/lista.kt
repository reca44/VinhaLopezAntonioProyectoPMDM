package com.murallaromana.dam.activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.adapters.ListaPersonajesAdapter
import com.murallaromana.dam.model.entities.Personaje
import com.murallaromana.dam.model.data.PersonajesDaoMockImpl


class lista : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //obtenemos datos
        val personajesDao = PersonajesDaoMockImpl()
        val listaPersonajes = personajesDao.getTodos()
    //creamos componentes
        val layoutManager = LinearLayoutManager(this)
        val adapter = ListaPersonajesAdapter(listaPersonajes)

        //asociamos el recyclesview con sus componentes
        binding.rvListaPersonajes.adapter=adapter
        binding.rvListaPersonajes.layoutManager=layoutManager
        binding.rvListaPersonajes.setHasFixedSize(true)


/*       val p = Personaje(
            0,
            "jonh",
            "nieve",
            "pringao",
            "bastardo",
            "https://thronesapi.com/assets/images/jon-snow.jpg"
        )
        val p2 = Personaje(
            0,
            "Daenerys",
            "dragonera",
            "pringao",
            "bastardo",
            "https://thronesapi.com/assets/images/daenerys.jpg"
        )
        val p3 = Personaje(
            0,
            "Arya ",
            "Stark",
            "jefa",
            "stark",
            "https://thronesapi.com/assets/images/arya-stark.jpg"
        )
        val p4 = Personaje(
            0,
            "Brandon",
            "Stark",
            "amish",
            "Stark",
            "https://thronesapi.com/assets/images/bran-stark.jpg"
        )

        val listanumeros = listOf(p, p2, p3, p4)*/
    }
}