package com.bahaa.exo2_tp4_stockage_sur_serveur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_team.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)



        var listPlayer:List<Player>

        val call = RetrofitService.endpoint.getPlayersByTeam(intent!!.extras.getString("idTeam").toString())
        call.enqueue(object : Callback<List<Player>> {
            override fun onFailure(call: Call<List<Player>>, t: Throwable) {
                Toast.makeText(this@TeamActivity," failed connecting server", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Player>>, response: Response<List<Player>>) {
                if(response?.isSuccessful!!){
                    //getting teams
                    listPlayer = response.body()!!
                    val listPlayerName:ArrayList<String> = ArrayList<String>()
                    for(item in listPlayer!!){
                        listPlayerName.add(item.first_name +"   "+item.last_name)
                    }

                    val adapter = ArrayAdapter<String>(this@TeamActivity, android.R.layout.simple_list_item_1, listPlayerName)
                    listViewPlayers.adapter=adapter

                }else {
                    Toast.makeText(this@TeamActivity,response.errorBody().toString()+" error connecting server", Toast.LENGTH_LONG).show()
                }            }
        })
    }
}
