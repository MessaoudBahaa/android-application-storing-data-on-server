package com.bahaa.exo2_tp4_stockage_sur_serveur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_team.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_team)

        addTeamButton.setOnClickListener(){

        val call = RetrofitService.endpoint.addTeam(Team(10,team_name_textview.text.toString(),continent_textview.text.toString()))
        call.enqueue(object:Callback<String> {

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@AddTeamActivity," failed connecting server", Toast.LENGTH_LONG).show()            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response?.isSuccessful!!){
                    //getting teams
                   // Toast.makeText(this@AddTeamActivity,response.body().toString()+" successful", Toast.LENGTH_LONG).show()



                }else {
                    Toast.makeText(this@AddTeamActivity,response.errorBody().toString()+" error connecting server", Toast.LENGTH_LONG).show()
                }

            }
        })

            val detailIntent = Intent(this, MainActivity::class.java)

            // 3
            startActivity(detailIntent)

        }
    }
}
