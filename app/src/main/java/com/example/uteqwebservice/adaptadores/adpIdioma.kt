package com.example.uteqwebservice.adaptadores

import com.example.uteqwebservice.modelos.Idioma
import java.util.ArrayList

class adpIdioma(lstIdiomas: ArrayList<Idioma>) {
    var datos: List<Idioma>? = null

    //Constructor
    fun adpIdioma(datos: ArrayList<Idioma>?) {
        this.datos = datos
    }
}