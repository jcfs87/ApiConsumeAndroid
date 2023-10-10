package com.juanfaria_jorgeseoane.proyectouf7juanyjorge

import retrofit2.Call
import retrofit2.http.GET
interface APIBodega {
    @GET("places")
    fun getListOfPlaces():Call <List<Bares>>
}