package com.mayur.shaadiapp.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {
        val baseUrl = "https://randomuser.me/api/" //?results=10

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                .build()

        }

        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // RxJava
    }
}