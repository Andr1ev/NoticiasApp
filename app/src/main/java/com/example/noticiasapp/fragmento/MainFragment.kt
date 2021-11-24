package com.example.noticiasapp.fragmento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticiasapp.R
import com.example.noticiasapp.adapter.NoticiaAdapter
import com.example.noticiasapp.adapter.adapter
import com.example.noticiasapp.adapter.noticiasDatos
import com.example.noticiasapp.clase.Noticia
import com.example.noticiasapp.clase.apiService
import com.example.noticiasapp.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var b: FragmentMainBinding
    private lateinit var adapter: NoticiaAdapter
    private val noticiasDatos = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = FragmentMainBinding.inflate(inflater, container, false)
        b.svNews.setOnQueryTextListener(this)

        initRecyclerView()


        return b.root
    }

    private fun initRecyclerView() {
        adapter = NoticiaAdapter(noticiasDatos)
        b.rvLista.layoutManager = LinearLayoutManager(this)
        b.rvLista.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/top-headlines?country=ar&category=sports&apiKey=f98b58a730e04c5595c5a90cbf27c7ec")//url de donde consultaremos sin poner el valor a consultar
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchNews(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            //corrutina, permite que tod0 lo siguiente se haga en un hilo distinto
            val call = getRetrofit().create(apiService::class.java)
                .getNoticias("https://newsapi.org/v2/top-headlines?q=$query&country=ar&category=sports&apiKey=f98b58a730e04c5595c5a90cbf27c7ec")
            //se pasa la url del json+imagenes para consultarlas
            val not: Noticia? = call.body()
            runOnUiThread {//permite volver al hilo principal
                if (call.isSuccessful) {
                    val images = not?.imagen ?: emptyList()
                    noticiasDatos.clear()
                    noticiasDatos.addAll(images)
                    adapter.notifyDataSetChanged()

                } else {
                    showError()
                }
                hideKeyboard()
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(b.root.windowToken, 0)
        //funcion para esconder el teclado al realizar la busqueda
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchNews(query.lowercase(Locale.getDefault()))
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}