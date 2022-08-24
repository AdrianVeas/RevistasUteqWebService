package com.example.uteqwebservice.modelos
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class EdicionesModelo (a: JSONObject) {
    var id_ediciones: String? = null
    var urlimage_ediciones: String? = null
    var descripcion: String ? = null

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): java.util.ArrayList<EdicionesModelo> {
            val ediciones: ArrayList<EdicionesModelo> = ArrayList()
            var i = 0
            while (i < datos.length()) {
                ediciones.add(EdicionesModelo(datos.getJSONObject(i)))
                i++
            }
            return ediciones
        }
    }


    init {
        id_ediciones = a.getString("issue_id")
        urlimage_ediciones = a.getString("cover")
        descripcion = "Volumen: "+ a.getString("volume") + "\nNumber: "+ a.getString("number") + "\nYear: " + a.getString("year")
    }
}