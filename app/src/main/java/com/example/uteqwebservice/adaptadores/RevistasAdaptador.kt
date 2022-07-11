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
import com.example.uteqwebservice.EdicionesActivity
import com.example.uteqwebservice.R
import com.example.uteqwebservice.modelos.RevistasModelo
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class RevistasAdaptador (val RevistasList: ArrayList<RevistasModelo>, var idioma: String?) : RecyclerView.Adapter<RevistasAdaptador.ViewHolder>() {

    var id_revistas : String? = ""

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.crdvwrevistas, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNombre.text = RevistasList[position].nombre_revista
        id_revistas = RevistasList[position].id_revista
        Picasso.get().load(RevistasList[position].urlimage_revista).into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return RevistasList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var txtNombre: TextView


        init {
            txtNombre = itemView.findViewById(R.id.txtNombreRevista)
            itemImage = itemView.findViewById(R.id.imgRevistas)
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                id_revistas = RevistasList[position].id_revista
                Snackbar.make(
                    v, "Item Selecccionado $id_revistas",
                    Snackbar.LENGTH_LONG
                ).setAction("Actción", null).show()

                val intent = Intent(v.context,EdicionesActivity::class.java)
                val bundle = Bundle()
                bundle.putString("idioma", idioma)
                bundle.putString("id", id_revistas)
                intent.putExtras(bundle)
                startActivity(v.context, intent, null)
            }
        }
    }
}