var mysql = require('mysql');
var config = require('./config.json');
var importQuery = require('./query.json');
var query;


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
   
   
    
   query=importQuery.UV+event.Valutazione+importQuery.UV2+"'"+event.Data+"'"+importQuery.UV3+event.idValutazione;

       
   

    connection.query(query, function (error, results, fields) {
      
      connection.release();
      
      if (error) callback(error);
      
      

      else callback(null,{ 
              statusCode:200,
              body : "AGGIORNATO CORRETTAMENTE",
              headers : {'Content-Type': 'application/json'}
    });
  });
})};