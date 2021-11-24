package com.example.noticiasapp.clase

import com.google.gson.annotations.SerializedName

data class Noticia (

    @SerializedName("author") var autor:String,
    @SerializedName("title")var titulo:String,
    @SerializedName("url") var url:String,
    @SerializedName("urlToImage")var imagen:String,
    @SerializedName("publishedAt") var fecha:String,
    @SerializedName("content")var noticia:String


)