package com.example.uteqwebservice

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.content.res.Configuration
import android.graphics.Color
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uteqwebservice.adaptadores.adpIdioma;
import com.example.uteqwebservice.modelos.Idioma;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


class MainActivity : AppCompatActivity() {
    var btnContinuar: Button? = null
    private val list_of_items: ArrayList<String> = ArrayList()
    var adaptador: adpIdioma? = null


    private val mAlertItems: Array<CharSequence>
    private val mSelectedItems: BooleanArray
    private val location: String? = null
    var mensaje: TextView? = null
    var seleccionar: TextView? = null
    var ingresar: Button? = null
    var Slenguajes: Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#1B8300")
        }
        Slenguajes = findViewById<View>(R.id.spinner) as Spinner
        mensaje = findViewById<View>(R.id.textView3) as TextView
        seleccionar = findViewById<View>(R.id.textView5) as TextView
        ingresar = findViewById<View>(R.id.btnContinuar) as Button

        handleSSLHandshake()

        //WebService

        //WebService
        val datos: Map<String, String> = HashMap()
        val ws = WebService(
            "https://revistas.uteq.edu.ec/ws/site.php",
            datos, this@MainActivity, this@MainActivity
        )
        ws.execute("GET")

        btnContinuar = findViewById<View>(R.id.btnContinuar) as Button
        btnContinuar!!.setOnClickListener(View.OnClickListener {
            val text = Slenguajes!!.selectedItem.toString()
            /*  if (text=="Español"){
                        text="es_ES";
                    }else {
                        text="en_US";
                    }*/iniciar(text)
        })

    }
    fun iniciar(idioma: String) {
        val prefs = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("Idioma", idioma)
        editor.commit()
        if (idioma == "es_ES") {
            val localizacion = Locale("es", "ES")
            Locale.setDefault(localizacion)
            val config = Configuration()
            config.locale = localizacion
            baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        }
        if (idioma == "en_US") {
            val localizacion = Locale("en", "US")
            Locale.setDefault(localizacion)
            val config = Configuration()
            config.locale = localizacion
            baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        }
        if (idioma == "pt_BR") {
            val localizacion = Locale("pt", "BR")
            Locale.setDefault(localizacion)
            val config = Configuration()
            config.locale = localizacion
            baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        }
        val intent = Intent(this, activityPrincipal::class.java)
        intent.putExtra("local", idioma)
        startActivity(intent)
        finish()
    }

    @Throws(JSONException::class)
    fun processFinish(result: String?) {
        val lstIdiomas: ArrayList<Idioma?>
        try {
            //Procesar APIRest Response
            val JSONlistaRevistas = JSONArray(result)
            lstIdiomas = Idioma.JsonObjectsBuild(JSONlistaRevistas)
            adaptador = adpIdioma(lstIdiomas)
            val idiomas = arrayOfNulls<String>(adaptador.datos.get(0).getIdiomas().size())
            val tamaño: Int = adaptador.datos.get(0).getIdiomas().size()
            for (i in 0 until tamaño) {
                idiomas[i] = adaptador.datos.get(0).getIdiomas().get(i)
            }
            Slenguajes!!.adapter =
                ArrayAdapter(applicationContext, R.layout.lyt_spinneritem, idiomas)
            Slenguajes!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View,
                    i: Int,
                    l: Long
                ) {
                    if (i == 1) {
                        mensaje!!.text = "Welcome to our mobile portal of scientific journals"
                        seleccionar!!.text = "Select a language to display"
                        ingresar.setText("ENTER")
                    } else {
                        mensaje!!.text = "Bienvenido a nuestro portal móvil de revistas científicas"
                        seleccionar!!.text = "Seleccione un idioma a mostrar"
                        ingresar.setText("INGRESAR")
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {} //add some code here
            }
        } catch (e: JSONException) {
            Toast.makeText(this.applicationContext, e.message, Toast.LENGTH_LONG)
        }
    }

    fun handleSSLHandshake() {
        try {
            val trustAllCerts: Array<TrustManager> =
                arrayOf<TrustManager>(object : X509TrustManager() {
                    val acceptedIssuers: Array<Any?>?
                        get() = arrayOfNulls(0)

                    fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
                    fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
                })
            val sc: SSLContext = SSLContext.getInstance("SSL")
            sc.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
            HttpsURLConnection.setDefaultHostnameVerifier(object : HostnameVerifier() {
                fun verify(arg0: String?, arg1: SSLSession?): Boolean {
                    return true
                }
            })
        } catch (ignored: Exception) {
        }
    }
}