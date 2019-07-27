package com.bahaa.exo2_tp4_stockage_sur_serveur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var listTeam:List<Team> = ArrayList<Team>()
        val call = RetrofitService.endpoint.getTeams()
        call.enqueue(object : Callback<List<Team>>{
            override fun onFailure(call: Call<List<Team>>, t: Throwable) {
                Toast.makeText(this@MainActivity," failed connecting server", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Team>>, response: Response<List<Team>>) {
                if(response?.isSuccessful!!){
                    //getting teams
                    listTeam = response.body()!!
                    val listTeamName:ArrayList<String> = ArrayList<String>()
                    for(item in listTeam!!){
                        listTeamName.add(item.team_name)
                        }

                    val adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, listTeamName)
                    listViewTeams.adapter=adapter

                }else {
                    Toast.makeText(this@MainActivity,response.errorBody().toString()+" error connecting server", Toast.LENGTH_LONG).show()
                }
            }



        })
        listViewTeams.setOnItemClickListener { _, _, position, _ ->
            // 1
            val selectedTeam = listTeam[position]

            // 2
            val detailIntent = Intent(this, TeamActivity::class.java)
            detailIntent.putExtra("nomTeam",selectedTeam.team_name!!)
            detailIntent.putExtra("idTeam",selectedTeam.id_team.toString()!!)

            // 3
            startActivity(detailIntent)
        }

        addTeamButton.setOnClickListener(){
            val intenttoaddteam = Intent(this, AddTeamActivity::class.java)
            startActivity(intenttoaddteam)
        }

    }
}