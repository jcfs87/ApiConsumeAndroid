package com.juanfaria_jorgeseoane.proyectouf7juanyjorge

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Bares (

    @PrimaryKey var name: String,
    @SerializedName("address") var address: String,
    @SerializedName("city") var city: String,
    @SerializedName("speciality") var speciality: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("web") var web: String
)