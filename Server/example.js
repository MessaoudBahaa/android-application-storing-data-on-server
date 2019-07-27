var express = require("express");
var mysql = require("mysql");
var app = express();
var bodyparser = require('body-parser');
app.use(bodyparser.json());


//database connection
var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : '',
    database:'footdb'
});
connection.connect();


// server creation

var server = app.listen(8082,function(){
    var host = server.address().address
    var port = server.address().port
});



// rest service
app.get('/getplayersbyteam/:teamId',function(req,res){  
    console.log(req.params.teamId);
    var query = "select * from teams join players where teams.id_team=players.id_team and teams.id_team = "+req.params.teamId; 
    
   connection.query(query,function(error,results){
    if (error) throw error;
    res.send(JSON.stringify(results));
})
});



// adding new team 
app.post('/addTeam',function(req,res){
   
    var team=req.body;
    
    var sql = "INSERT INTO teams( team_name, continent) VALUES (?,?)";
    var values = 
      [team.team_name, team.continent]
    connection.query(sql, values, function (err, result) {
      if (err) throw err;
      console.log("Number of records inserted: " + result.affectedRows);
    });


});


// rest service
app.get('/getteams',function(req,res){  
    var query = "select * from teams"; 
   connection.query(query,function(error,results){
    if (error) throw error;
    res.send(JSON.stringify(results));
})
});


