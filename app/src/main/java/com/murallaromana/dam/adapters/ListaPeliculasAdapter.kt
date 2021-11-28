package com.murallaromana.dam.adapters
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murallaromana.dam.R
import com.murallaromana.dam.activities.DetallesActivity
import com.murallaromana.dam.model.entities.Pelicula
import com.squareup.picasso.Picasso

class ListaPeliculasAdapter(private val peliculas: List<Pelicula>,val context: Context):RecyclerView.Adapter<ListaPeliculasAdapter.PeliculasViewHolder>() {

    class PeliculasViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val tvTitulo: TextView =itemView.findViewById(R.id.tvTitulo)
        val ivFoto: ImageView = itemView.findViewById(R.id.ivFoto)
        val tvGenero: TextView =itemView.findViewById(R.id.tvGenero)
        val tvDirector: TextView =itemView.findViewById(R.id.tvDirector)
        val tvNota: TextView =itemView.findViewById(R.id.tvNota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context).inflate(R.layout.activity_item_pelicula,parent,false)

        return PeliculasViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        val pelicula = peliculas[position]
        holder.tvTitulo.text = pelicula.titulo
        holder.tvGenero.text = pelicula.genero
        holder.tvNota.text = pelicula.puntuacion
        holder.tvDirector.text = pelicula.director
        Picasso.get().isLoggingEnabled = true
        Picasso.get().load(pelicula.url).into(holder.ivFoto)

       holder.itemView.setOnClickListener {
           val intent = Intent(holder.itemView.context, DetallesActivity::class.java)
           intent.putExtra("pelicula",pelicula)
           holder.itemView.context.startActivity(intent)
       }
    }

    override fun getItemCount(): Int {
       return peliculas.size
    }
}