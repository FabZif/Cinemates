var mysql = require('mysql');
var config = require('./config.json');
var importQuery = require('./query.json');
var filter;
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
   
   
    switch (event.filtro) {
      
      case 'idListaPreferiti':
           query=importQuery.IV+"( "+event.Valutazione+",'"+event.Data+"',"+event.idValutatore+","+event.idListaPreferiti+","+"null"+")";
        break;
      case 'idListaDaVedere':
           query=importQuery.IV+"( "+event.Valutazione+",'"+event.Data+"',"+event.idValutatore+","+"null"+","+event.idListaDaVedere+")";

         break;
   }

    console.log(importQuery.IV+filter);
    connection.query(query, function (error, results, fields) {
      
      connection.release();
      
      if (error) callback(error);
      
      

      else callback(null,{ 
              statusCode:200,
              body : "INSERITO CORRETTAMENTE",
              headers : {'Content-Type': 'application/json'}
    });
  });
})};