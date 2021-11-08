package com.murallaromana.dam.model.entities

class Personaje(
    var id: Long,
    var nombre: String,
    var apellidos: String,
    var titulo: String,
    var familia: String,
    var url: String
) {
    fun muestraNombre(): String {
        return nombre+" "+apellidos
    }
    //otra forma
    fun getNombreCompleto2(): String{
        return "${this.nombre}  ${this.apellidos}"
    }
    //otra forma de hacerlo sin return
    fun getNombreCompleto3()="El nombre es: ${this.nombre} y el apellido es: ${this.apellidos}"
}