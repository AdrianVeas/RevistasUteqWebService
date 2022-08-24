package com.example.uteqwebservice.modelos

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GaleysModelo (a: JSONObject) {
    var galley_id : String? = ""
    var label : String? = ""
    var file_id : String? = ""
    var UrlViewGalley : String? = ""
    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): java.util.ArrayList<GaleysModelo> {
            val galeys: ArrayList<GaleysModelo> = ArrayList()
            var i = 0
            while (i < datos.length()) {
                galeys.add(GaleysModelo(datos.getJSONObject(i)))
                i++
            }
            return galeys
        }
    }


    init {
        galley_id = a.getString("galley_id")
        label = a.getString("label")
        file_id = a.getString("file_id")
        UrlViewGalley = a.getString("UrlViewGalley")
    }
}