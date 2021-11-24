package com.example.noticiasapp.clase

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiService {
    @GET
    fun getNoticias(@Url url:String): Response<Noticia>
}