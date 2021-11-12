package com.murallaromana.dam.adapters
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murallaromana.dam.R
import com.murallaromana.dam.activities.detallesActivity
import com.murallaromana.dam.model.entities.Pelicula
import com.squareup.picasso.Picasso

class ListaPeliculasAdapter(val peliculas: List<Pelicula>):RecyclerView.Adapter<ListaPeliculasAdapter.PersonajesViewHolder>() {

    class PersonajesViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val tvTitulo=itemView.findViewById<TextView>(R.id.tvTitulo)
        val ivFoto=itemView.findViewById<ImageView>(R.id.ivFoto)
        val tvGenero=itemView.findViewById<TextView>(R.id.tvGenero)
        //val tvUrl=itemView.findViewById<TextView>(R.id.tvUrl)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val LayoutInflater=LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula,parent,false)

        return PersonajesViewHolder(LayoutInflater)
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        val pelicula = peliculas.get(position)
        holder.tvTitulo.setText(pelicula.titulo)
        holder.tvGenero.setText(pelicula.genero)
        Picasso.get().isLoggingEnabled = true
        Picasso.get().load(pelicula.url).into(holder.ivFoto)

       holder.itemView.setOnClickListener {
           val intent = Intent(holder.itemView.context, detallesActivity::class.java)
           intent.putExtra("pelicula",pelicula)
           holder.itemView.context.startActivity(intent)
       }
    }

    override fun getItemCount(): Int {
       return peliculas.size
    }
}