package com.example.uteqwebservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uteqwebservice.adaptadores.EdicionesAdaptador
import com.example.uteqwebservice.modelos.EdicionesModelo
import com.example.uteqwebservice.modelos.EdicionesModelo.Companion.JsonObjectsBuild
import org.json.JSONArray
import java.util.ArrayList

class EdicionesActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EdicionesAdaptador.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ediciones)
        try {
            val bundle = intent.extras
            val idioma = bundle?.getString("idioma")
            val id = bundle?.getString("id")

            val txt = findViewById<TextView>(R.id.texttituloediciones)
            layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val recyclerView = findViewById<RecyclerView>(R.id.edicionesrecycler)
            recyclerView.layoutManager = layoutManager
            when (idioma) {
                "es_ES" -> {
                    txt.text = "EDICIONES DE LAS REVISTAS UTEQ"

                }
                "en_US" -> {
                    txt.text = "EDITIONS OF UTEQ MAGAZINES"
                }
                "pt_BR" -> {
                    txt.text = "EDIÇÕES DAS REVISTA UTEQ"
                }
            }

            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET,
                "https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + id + "&locale=" + idioma,
                { response ->

                    try {
                        val JSONlista = JSONArray(response)
                        val lstUsuarios: ArrayList<EdicionesModelo> = JsonObjectsBuild(JSONlista)

                        val resId = R.anim.layout_animation_down_to_up
                        val animation = AnimationUtils.loadLayoutAnimation(
                            this,
                            resId
                        )
                        recyclerView.layoutAnimation = animation

                        adapter = EdicionesAdaptador(lstUsuarios, idioma)

                        recyclerView.adapter = adapter
                    } catch (e: Exception) {
                        txt.text = e.message
                    }

                },
                { error -> txt.text = error.message })


            queue.add(stringRequest)

        }

        catch(e :Exception) {
            Toast.makeText(applicationContext, "Error: " + e.message, Toast.LENGTH_LONG).show();
        }
    }
}