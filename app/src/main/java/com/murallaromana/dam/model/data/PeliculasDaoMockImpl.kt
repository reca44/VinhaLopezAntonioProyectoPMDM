package com.murallaromana.dam.model.data

import com.murallaromana.dam.model.entities.Pelicula
import com.murallaromana.dam.model.entities.data.PeliculasDao


class PeliculasDaoMockImpl : PeliculasDao {

    override fun getTodos() = listOf(
        Pelicula(
            0,
            "tucker",
            "tucker y dale contra el mal",
            "absurda",
            "https://es.web.img3.acsta.net/medias/nmedia/18/89/43/26/20046420.jpg"
        ),
        Pelicula(
            0,
            "Venom",
            "Venom el animal",
            "Accion",
            "https://es.web.img3.acsta.net/c_310_420/pictures/21/08/31/16/41/4145554.jpg"
        ),
        Pelicula(
            0,
            "Solo en casa ",
            "Solo en casa 3",
            "Comedia",
            "https://m.media-amazon.com/images/I/51HXbOkZ44L.jpg"
        ),
        Pelicula(
            0,
            "DeadPool",
            "DeadPool Regresa",
            "Comedia",
            "https://es.web.img3.acsta.net/pictures/15/12/04/10/48/099822.jpg"
        )
    )
}


/*      Otra forma de hacerlo
        return listOf(
            Personaje(
                0,
                "jonh",
                "nieve",
                "pringao",
                "bastardo",
                "https://thronesapi.com/assets/images/jon-snow.jpg"
            ),
            Personaje(
                0,
                "Daenerys",
                "dragonera",
                "pringao",
                "bastardo",
                "https://thronesapi.com/assets/images/daenerys.jpg"
            ),
            Personaje(
                0,
                "Arya ",
                "Stark",
                "jefa",
                "stark",
                "https://thronesapi.com/assets/images/arya-stark.jpg"
            ),
            Personaje(
                0,
                "Brandon",
                "Stark",
                "amish",
                "Stark",
                "https://thronesapi.com/assets/images/bran-stark.jpg"
            )
        )

    }
}*/