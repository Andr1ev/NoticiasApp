package com.example.noticiasapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noticiasapp.R
import com.example.noticiasapp.databinding.FragmentMainBinding



class NoticiaAdapter(private val noticias: List<String>) :
    RecyclerView.Adapter<NoticiaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NoticiaViewHolder(layoutInflater.inflate(R.layout.item_layout, parent, false))

    }



    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val autor: String = noticias[position]
        val titulo: String = noticias[position]
        val url: String = noticias[position]
        val imagen: String = noticias[position]
        val fecha: String = noticias[position]
        val noticia: String = noticias[position]

        holder.bind(autor,titulo,url,imagen,fecha,noticia)
    }

    override fun getItemCount(): Int {
        return noticias.size
    }
}

