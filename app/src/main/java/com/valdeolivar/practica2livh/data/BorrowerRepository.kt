package com.valdeolivar.practica2livh.data

import com.valdeolivar.practica2livh.data.remote.BorrowerApi
import com.valdeolivar.practica2livh.data.remote.model.BorrowerDetailDto
import com.valdeolivar.practica2livh.data.remote.model.BorrowerDto
import retrofit2.Call
import retrofit2.Retrofit

class BorrowerRepository(
    private val retrofit: Retrofit
) {

    private val borrowersApi: BorrowerApi = retrofit.create(BorrowerApi::class.java)

    fun getBorrowers(url: String?): Call<MutableList<BorrowerDto>> =
        borrowersApi.getBorrowers(url)

    fun getBorrowersDetail(id: String?): Call<BorrowerDetailDto> =
        borrowersApi.getBorrowerDetail(id)


}