package com.valdeolivar.practica2livh.data.remote

import com.valdeolivar.practica2livh.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    fun getRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor().apply {
            //Para que el interceptor me de mensajes a nivel del body
            // de la operacion de la red

            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}