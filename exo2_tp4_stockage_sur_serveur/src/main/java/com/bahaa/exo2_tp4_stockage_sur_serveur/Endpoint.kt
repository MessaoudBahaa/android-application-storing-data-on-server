package com.bahaa.exo2_tp4_stockage_sur_serveur

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface Endpoint {
    @GET("getTeams")
    fun getTeams():Call<List<Team>>


    @GET("getplayersbyteam/{teamId}")
    fun getPlayersByTeam(@Path("teamId") teamId:String):Call<List<Player>>


    @POST("addTeam")
    fun addTeam(@Body team:Team):Call<String>
}