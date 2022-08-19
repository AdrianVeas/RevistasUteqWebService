package com.example.uteqwebservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uteqwebservice.adaptadores.ArticulosAdaptador
import com.example.uteqwebservice.modelos.ArticuloModelo
import com.example.uteqwebservice.modelos.ArticuloModelo.Companion.JsonObjectsBuild
import org.json.JSONArray
import java.util.ArrayList

class ArticulosActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ArticulosAdaptador.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)
        try {
            val bundle = intent.extras
            val idioma = bundle?.getString("idioma")

            val id = bundle?.getString("id")

            val txt = findViewById<TextView>(R.id.texttituloarticulos)
            layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val recyclerView = findViewById<RecyclerView>(R.id.articulosrecycler)
            recyclerView.layoutManager = layoutManager
            when (idioma) {
                "es_ES" -> {
                    txt.text = "ARTÍCULOS DE LAS REVISTAS UTEQ"

                }
                "en_US" -> {
                    txt.text = "ARTICLES FROM UTEQ MAGAZINES"
                }
                "pt_BR" -> {
                    txt.text = "ARTIGOS DA REVISTA UTEQ"
                }
            }

            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET, "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + id + "&locale=" + idioma,
                { response ->

                    try {
                        val JSONlista = JSONArray(response)
                        val lstUsuarios: ArrayList<ArticuloModelo> = JsonObjectsBuild(JSONlista)

                        val resId = R.anim.layout_animation_down_to_up
                        val animation = AnimationUtils.loadLayoutAnimation(
                            this,
                            resId
                        )
                        recyclerView.layoutAnimation = animation

                        adapter = ArticulosAdaptador(lstUsuarios, idioma)

                        recyclerView.adapter = adapter
                    }catch (e: Exception){
                        txt.text = e.message
                    }

                },
                { error ->  txt.text = error.message})


            queue.add(stringRequest)


        }
        catch (e :Exception ){
            Toast.makeText(applicationContext, "Error: "+e.message , Toast.LENGTH_SHORT).show()
        }
    }
    fun downloadpdf(v: View?) {
        Toast.makeText(applicationContext, "YEAH", Toast.LENGTH_SHORT).show()
    }
}