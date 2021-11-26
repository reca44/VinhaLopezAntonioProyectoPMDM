package com.murallaromana.dam.model.data

import com.murallaromana.dam.model.entities.Pelicula
import com.murallaromana.dam.model.entities.data.PeliculasDao


class PeliculasDaoMockImpl : PeliculasDao {

    override fun getTodos(): ArrayList<Pelicula> {
        val lista: ArrayList<Pelicula> = ArrayList()
        lista.addAll(
            listOf(
                Pelicula(
                    "10",
                    "Eli Craig",
                    "tucker y dale contra el mal",
                    "absurda",
                    "https://es.web.img3.acsta.net/medias/nmedia/18/89/43/26/20046420.jpg"

                ),
                Pelicula(
                    "6.0",
                    "Andy Serkis",
                    "Venom habrá matanza",
                    "Accion",
                    "https://es.web.img3.acsta.net/c_310_420/pictures/21/08/31/16/41/4145554.jpg"
                ),
                Pelicula(
                    "9.5",
                    "Raja Gosnell",
                    "Solo en casa 3",
                    "Comedia",
                    "https://m.media-amazon.com/images/I/51HXbOkZ44L.jpg"
                ),
                Pelicula(
                    "7.4",
                    "Tim Miller",
                    "DeadPool Regresa",
                    "Comedia",
                    "https://es.web.img3.acsta.net/pictures/15/12/04/10/48/099822.jpg"
                ), Pelicula(
                    "7.8",
                    "Jodie Comer",
                    "Free Guy ",
                    "Comedia",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vkMjBCg44TXhYxA2GNaXKDgFkOJ.jpg"
                ), Pelicula(
                    "7.9",
                    "Destin Daniel Cretton",
                    "Shang-Chi y la leyenda de los Diez Anillos",
                    "Acción",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9VqajJXm29uprSaxOcEh7O0d6E9.jpg"
                ), Pelicula(
                    "8.2",
                    "James Gunn",
                    "El escuadrón suicida ",
                    "Locura",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fPJWlhXA2VXf4MlQ3JenVsz1iba.jpg"
                )
            )
        )
        return lista
    }
}
