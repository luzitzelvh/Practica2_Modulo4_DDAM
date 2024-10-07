package com.valdeolivar.practica2livh.application

import android.app.Application
import com.valdeolivar.practica2livh.data.BorrowerRepository
import com.valdeolivar.practica2livh.data.remote.RetrofitHelper

class TandaApp: Application() {

    private val retrofit by lazy{
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy {
        BorrowerRepository(retrofit)
    }

}