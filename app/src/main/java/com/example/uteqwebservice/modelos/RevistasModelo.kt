package com.example.uteqwebservice.modelos

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RevistasModelo(a: JSONObject) {
    var id_revista: String? = null
    var urlimage_revista: String? = null
    var nombre_revista: String ? = null

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<RevistasModelo> {
            val revistas: ArrayList<RevistasModelo> = ArrayList()
            var i = 0
            while (i < datos.length()) {
                revistas.add(RevistasModelo(datos.getJSONObject(i)))
                i++
            }
            return revistas
        }
    }


    init {
        id_revista = a.getString("journal_id")
        urlimage_revista = a.getString("portada")
        nombre_revista = a.getString("name")
    }
}