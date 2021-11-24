package com.example.noticiasapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.noticiasapp.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso


class NoticiaViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val b = ItemLayoutBinding.bind(view)

    fun bind(autor:String, titulo:String,url:String,imagen:String,fecha:String,noticia:String){
        Picasso.get().load(imagen).into(b.ivImagen)

    }
}