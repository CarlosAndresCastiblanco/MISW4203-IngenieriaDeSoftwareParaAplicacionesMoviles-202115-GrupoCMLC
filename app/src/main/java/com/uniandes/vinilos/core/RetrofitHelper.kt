package com.uniandes.vinilos.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://test.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}