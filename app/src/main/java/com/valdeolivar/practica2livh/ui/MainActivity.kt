package com.valdeolivar.practica2livh.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.valdeolivar.practica2livh.R
import com.valdeolivar.practica2livh.data.BorrowerRepository
import com.valdeolivar.practica2livh.data.remote.RetrofitHelper
import com.valdeolivar.practica2livh.data.remote.model.BorrowerDto
import com.valdeolivar.practica2livh.databinding.ActivityMainBinding
import com.valdeolivar.practica2livh.ui.fragments.BorrowersListFragment
import com.valdeolivar.practica2livh.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*private lateinit var repository: BorrowerRepository
    private lateinit var retrofit: Retrofit*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Mostramos el fragment inicial
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BorrowersListFragment())
                .commit()
        }

        //instaciando retrofit
        /*retrofit = RetrofitHelper().getRetrofit()

        //Obteniendo el repositorio
        repository = BorrowerRepository(retrofit)*/

    }

    /*fun click(view: View) {

        val call: Call<MutableList<BorrowerDto>> = repository.getBorrowers("borrower/borrower_list")

        call.enqueue(object: Callback<MutableList<BorrowerDto>>{
            override fun onResponse(
                call: Call<MutableList<BorrowerDto>>,
                response: Response<MutableList<BorrowerDto>>
            ) {
                Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.body()}")

            }

            override fun onFailure(p0: Call<MutableList<BorrowerDto>>, p1: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Error: No hay conexion",
                    Toast.LENGTH_SHORT).show()
            }

        })

    }*/
}