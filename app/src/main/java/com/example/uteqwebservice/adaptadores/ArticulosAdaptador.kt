package com.example.uteqwebservice.adaptadores

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.uteqwebservice.R
import com.example.uteqwebservice.ViewPDFActivity
import com.example.uteqwebservice.modelos.ArticuloModelo
import com.google.android.material.snackbar.Snackbar

class ArticulosAdaptador (val ArticulosList: ArrayList<ArticuloModelo>, var idioma: String?) : RecyclerView.Adapter<ArticulosAdaptador.ViewHolder>() {
    var id_articulos : String? = ""
    var btnviewpdf : Button?

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.crdvwarticulos, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNombre.text = ArticulosList[position].titulo
        holder.txtdoi.text = ArticulosList[position].doi
        holder.txtfecha.text = ArticulosList[position].fecha

        id_articulos = ArticulosList[position].id_ediciones
    }

    override fun getItemCount(): Int {
        return ArticulosList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtNombre: TextView
        var txtdoi: TextView
        var txtfecha: TextView


        init {
            txtNombre = itemView.findViewById(R.id.txtNombreArticulo)
            txtdoi = itemView.findViewById(R.id.txtDoiArticulo)
            txtfecha = itemView.findViewById(R.id.txtFechaArticulo)
            btnviewpdf = itemView.findViewById(R.id.btnViewPdf)

            btnviewpdf.setOnClickListener { v:View->
                val position: Int = adapterPosition
                id_articulos = ArticulosList[position].id_ediciones
                Snackbar.make(
                    v, "Item Selecccionado $id_articulos",
                    Snackbar.LENGTH_LONG
                ).setAction("Actción", null).show()

                val intent = Intent(v.context, ViewPDFActivity::class.java)
                val bundle = Bundle()
                bundle.putString("idioma", idioma)
                bundle.putString("id", id_articulos)
                intent.putExtras(bundle)
                ContextCompat.startActivity(v.context, intent, null)
            }
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                id_articulos = ArticulosList[position].id_ediciones
                Snackbar.make(
                    v, "Item Selecccionado $id_articulos",
                    Snackbar.LENGTH_LONG
                ).setAction("Actción", null).show()

                val intent = Intent(v.context, ViewPDFActivity::class.java)
                val bundle = Bundle()
                bundle.putString("idioma", idioma)
                bundle.putString("id", id_articulos)
                intent.putExtras(bundle)
                ContextCompat.startActivity(v.context, intent, null)
            }
        }
    }
}