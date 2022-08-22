package com.example.uteqwebservice

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.example.uteqwebservice.adaptadores.RevistasAdaptador
import com.example.uteqwebservice.extras.HttpsTrustManager
import com.example.uteqwebservice.extras.MySingleton
import com.example.uteqwebservice.modelos.RevistasModelo
import com.example.uteqwebservice.modelos.RevistasModelo.Companion.JsonObjectsBuild
import org.json.JSONArray

class RevistasActivity : AppCompatActivity() , View.OnClickListener{
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RevistasAdaptador.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revistas)
        try {
            val bundle = intent.extras
            val idioma = bundle?.getString("idioma")

            val txt = findViewById<TextView>(R.id.texttitulo)
            layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val recyclerView = findViewById<RecyclerView>(R.id.revistasrecycler)
            recyclerView.layoutManager = layoutManager
            when (idioma) {
                "es_ES" -> {
                    txt.text = "LISTAS DE REVISTAS UTEQ"

                }
                "en_US" -> {
                    txt.text = "UTEQ MAGAZINE LISTS"
                }
                "pt_BR" -> {
                    txt.text = "LISTAS DA REVISTA UTEQ"
                }
            }

            HttpsTrustManager.allowAllSSL()
            val request = StringRequest(
                Request.Method.GET, "https://revistas.uteq.edu.ec/ws/journals.php?locale=$idioma",
                { response ->

                        val JSONlista = JSONArray(response)
                        val lstUsuarios: ArrayList<RevistasModelo> = JsonObjectsBuild(JSONlista)

                        val resId = R.anim.layout_animation_down_to_up
                        val animation = AnimationUtils.loadLayoutAnimation(
                          this,
                        resId
                        )
                        recyclerView.layoutAnimation = animation

                        adapter = RevistasAdaptador(lstUsuarios, idioma)

                        recyclerView.adapter = adapter

                },
                { error ->  txt.text = error.message
                    Toast.makeText(applicationContext, "Error 2: "+error.message , LENGTH_SHORT).show()})


            MySingleton.getInstance(applicationContext).addToRequestQueue(request)


        }
        catch (e :Exception ){
            Toast.makeText(applicationContext, "Error: "+e.message , LENGTH_SHORT).show()
        }
    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}