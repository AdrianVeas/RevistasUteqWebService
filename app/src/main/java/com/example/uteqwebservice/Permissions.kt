package com.example.uteqwebservice

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class Permissions (var context: Context) {
    fun getPermisosNoAprobados(listaPermisos: ArrayList<String?>): ArrayList<String?> {
        val list = ArrayList<String?>()
        for (permiso in listaPermisos) {
            if (context.checkSelfPermission(permiso!!) != PackageManager.PERMISSION_GRANTED)
                list.add(permiso)
        }
        return list
    }

    fun getPermisosAprobados(listaPermisos: ArrayList<String?>): ArrayList<String?> {
        val list = ArrayList<String?>()
        for (permiso in listaPermisos) {
            if (context.checkSelfPermission(permiso!!) == PackageManager.PERMISSION_GRANTED)
                list.add(permiso)
        }
        return list
    }

    fun getPermission(permisosSolicitados: ArrayList<String?>) {
        if(permisosSolicitados.size>0)
            ActivityCompat.requestPermissions(context as Activity, permisosSolicitados.toArray(arrayOfNulls(permisosSolicitados.size)),1)
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,grantResults: IntArray):String  {
        var s = ""
        if (requestCode == 1) {
            for (i in permissions.indices) {
                s+= if(grantResults[i] == PackageManager.PERMISSION_GRANTED)"Permitido: " else "Denegado: "
                s+=" " + permissions[i] + "\n"
            }
        }
        return s
    }
}