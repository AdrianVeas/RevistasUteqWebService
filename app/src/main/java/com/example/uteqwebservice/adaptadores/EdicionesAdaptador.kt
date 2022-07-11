package com.example.uteqwebservice.adaptadores
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.uteqwebservice.ArticulosActivity
import com.example.uteqwebservice.EdicionesActivity
import com.example.uteqwebservice.R
import com.example.uteqwebservice.modelos.EdicionesModelo
import com.example.uteqwebservice.modelos.RevistasModelo
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
class EdicionesAdaptador (val EdicionesList: ArrayList<EdicionesModelo>, var idioma: String?) : RecyclerView.Adapter<EdicionesAdaptador.ViewHolder>()  {
    var id_ediciones : String? = ""

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.crdvwediciones, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNombre.text = EdicionesList[position].descripcion
        id_ediciones = EdicionesList[position].id_ediciones
        Picasso.get().load(EdicionesList[position].urlimage_ediciones).into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return EdicionesList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var txtNombre: TextView


        init {
            txtNombre = itemView.findViewById(R.id.txtNombreEdiciones)
            itemImage = itemView.findViewById(R.id.imgEdiciones)
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                id_ediciones = EdicionesList[position].id_ediciones
                Snackbar.make(
                    v, "Item Selecccionado $id_ediciones",
                    Snackbar.LENGTH_LONG
                ).setAction("Actción", null).show()

                val intent = Intent(v.context,ArticulosActivity::class.java)
                val bundle = Bundle()
                bundle.putString("idioma", idioma)
                bundle.putString("id", id_ediciones)
                intent.putExtras(bundle)
                startActivity(v.context, intent, null)
            }
        }
    }
}