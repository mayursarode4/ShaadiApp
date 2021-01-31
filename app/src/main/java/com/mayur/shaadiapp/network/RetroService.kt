package com.mayur.shaadiapp.network

import com.mayur.shaadiapp.model.NameListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("?")
    fun getNameListFromApi(@Query("results") query: String): Call<NameListModel>
}