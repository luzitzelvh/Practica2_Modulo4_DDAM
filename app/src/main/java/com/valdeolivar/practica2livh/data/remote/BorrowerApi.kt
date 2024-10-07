package com.valdeolivar.practica2livh.data.remote


import com.valdeolivar.practica2livh.data.remote.model.BorrowerDetailDto
import com.valdeolivar.practica2livh.data.remote.model.BorrowerDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface BorrowerApi {

    //Endpoints

    //borrower/borrower_list
    @GET
    fun getBorrowers(
        @Url url: String?
    ): Call<MutableList<BorrowerDto>>
    //Se mandaria a llamar getBorrower("borrower/borrower_list")



    @GET("borrower/borrower_list")
    fun getBorrowers(): Call<MutableList<BorrowerDto>>
    //Se mandaria a llamar getBorrower()



    //borrower/borrower_list/213579
    @GET("borrower/borrower_list/{id}")
    fun getBorrowerDetail(
        @Path("id") id: String?
    ): Call<BorrowerDetailDto>
    //Se mandaria a llamar asi: getGameDetail("213579")



}