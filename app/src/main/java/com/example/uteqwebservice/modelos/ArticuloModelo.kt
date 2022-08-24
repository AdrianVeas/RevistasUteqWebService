package com.example.uteqwebservice.modelos
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
class ArticuloModelo (a: JSONObject) {
    var id_ediciones: String? = null
    var titulo: String? = null
    var doi: String ? = null
    var fecha: String ? = null
    var galeys: JSONArray? =null

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): java.util.ArrayList<ArticuloModelo> {
            val articulos: ArrayList<ArticuloModelo> = ArrayList()
            var i = 0
            while (i < datos.length()) {
                articulos.add(ArticuloModelo(datos.getJSONObject(i)))
                i++
            }
            return articulos
        }
    }


    init {
        id_ediciones = a.getString("publication_id")
        titulo = a.getString("title")
        doi = a.getString("doi")
        fecha = a.getString("date_published")
        galeys = a.getJSONArray("galeys")
    }
}