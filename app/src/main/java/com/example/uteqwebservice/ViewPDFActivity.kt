package com.example.uteqwebservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.uteqwebservice.adaptadores.ArticulosAdaptador

class ViewPDFActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ArticulosAdaptador.ViewHolder>? = null
    private lateinit var id : String
    private lateinit var idioma : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf)
        try {
            val bundle = intent.extras
            idioma = bundle?.getString("idioma").toString()

            id = bundle?.getString("id").toString()
        }catch(e: Exception){
            Toast.makeText(applicationContext, "Error: "+e.message , Toast.LENGTH_LONG).show()
        }
    }
}