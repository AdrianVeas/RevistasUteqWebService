package com.example.uteqwebservice

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.uteqwebservice.extras.Permissions


class MainActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var adminPermisos: Permissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spineridiomas = findViewById<View>(R.id.spinner) as Spinner
        val listaidiomas = listOf("ESPAÑOL","ENGLISH", "PORTUGUêS")
        val adaptadoridiomas = ArrayAdapter(this, R.layout.spinner_team, listaidiomas)

        spineridiomas.adapter = adaptadoridiomas


        adminPermisos = Permissions(this)

        val permisosSolicitados = ArrayList<String?>()
        permisosSolicitados.add(Manifest.permission.CAMERA)
        permisosSolicitados.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        permisosSolicitados.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        permisosSolicitados.add(Manifest.permission.WRITE_CALENDAR)


        val permisosAprobados:ArrayList<String?>   = adminPermisos.getPermisosAprobados(permisosSolicitados)
        val listPermisosNOAprob:ArrayList<String?> = adminPermisos.getPermisosNoAprobados(permisosSolicitados)

        adminPermisos.getPermission(listPermisosNOAprob)


    }

    override fun onClick(v: View?) {
        val spineridiomas = findViewById<View>(R.id.spinner) as Spinner
        val text: String = spineridiomas.selectedItem.toString()
        val idioma :String
        val intent = Intent(this, RevistasActivity::class.java)
        val bundle = Bundle()
        when (text) {
            "ESPAÑOL" -> {
                Toast.makeText(applicationContext, "BIENVENIDO", Toast.LENGTH_SHORT).show()
                idioma = "es_ES"
                bundle.putString("idioma", idioma)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            "ENGLISH" -> {
                Toast.makeText(applicationContext, "WELCOME", Toast.LENGTH_SHORT).show()
                idioma = "en_US"
                bundle.putString("idioma", idioma)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            "PORTUGUêS" -> {
                Toast.makeText(applicationContext, "BEM VINDO", Toast.LENGTH_SHORT).show()
                idioma = "pt_BR"
                bundle.putString("idioma", idioma)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            else -> {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


}