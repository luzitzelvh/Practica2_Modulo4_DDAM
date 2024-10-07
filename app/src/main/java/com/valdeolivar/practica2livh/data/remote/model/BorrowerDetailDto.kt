package com.valdeolivar.practica2livh.data.remote.model

import com.google.gson.annotations.SerializedName

data class BorrowerDetailDto(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("surname")
    var surname: String? = null,

    @SerializedName("alias")
    var alias: String? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("estado")
    var state: String? = null

)
