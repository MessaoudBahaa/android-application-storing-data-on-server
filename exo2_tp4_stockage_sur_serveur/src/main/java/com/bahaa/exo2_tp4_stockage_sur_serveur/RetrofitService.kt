package com.bahaa.exo2_tp4_stockage_sur_serveur

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoint :Endpoint by lazy{
        Retrofit.Builder().baseUrl("http://192.168.0.5:8082/").
                addConverterFactory(GsonConverterFactory.create()).build().create(Endpoint::class.java)
    }
}