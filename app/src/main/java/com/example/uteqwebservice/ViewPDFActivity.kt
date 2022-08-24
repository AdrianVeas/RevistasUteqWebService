package com.example.uteqwebservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.uteqwebservice.adaptadores.ArticulosAdaptador

class ViewPDFActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ArticulosAdaptador.ViewHolder>? = null
    private lateinit var id : String
    private lateinit var idioma : String
    private lateinit var url : String
    private lateinit var txttitulo : TextView
    private lateinit var webview : WebView
    private lateinit var titulo : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf)
        try {
            val bundle = intent.extras
            idioma = bundle?.getString("idioma").toString()
            titulo =bundle?.getString("title").toString()
            id = bundle?.getString("id").toString()
            url=bundle?.getString("url").toString()
            txttitulo = findViewById(R.id.txttituloviewpdf)
            txttitulo.text = titulo

            webview = findViewById(R.id.webview)
            //webview.loadUrl(url)
            webview.loadUrl("https://developer.android.com/guide/webapps/webview")

        }catch(e: Exception){
            Toast.makeText(applicationContext, "Error: "+e.message , Toast.LENGTH_LONG).show()
        }
    }
}