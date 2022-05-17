var mysql = require('mysql');
var config = require('./config.json');
var importQuery = require('./query.json');
var filter;


var pool  = mysql.createPool({
    host     : config.dbhost,
    user     : config.dbuser,
    password : config.dbpassword,
    database : config.dbname,
    port : config.dbport
  });

  exports.handler =  (event, context, callback) => { 

  context.callbackWaitsForEmptyEventLoop = false;
  pool.getConnection(function(err, connection) { 
     
  if(err) console.log("Error");
   
   
    if((event.Email&&event.Nome&&event.Cognome&&event.Nickname&&event.Password)!=""){
    filter="( '"+event.Nome+"','"+event.Cognome+"','"+event.Email+"','"+event.Nickname+"','"+event.Password+"')";}
    else{
      callback("ERROR");
    }
    console.log(importQuery.IU+filter);
    connection.query(importQuery.IU+filter, function (error, results, fields) {
      
      connection.release();
      
      if (error) callback(error);
      
      

      else callback(null,{ 
              statusCode:200,
              body : "INSERITO CORRETTAMENTE",
              headers : {'Content-Type': 'application/json'}
    });
  });
})};