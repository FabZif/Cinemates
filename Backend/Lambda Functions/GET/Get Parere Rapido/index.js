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
      
      case 'ContaMiPiacePreferiti':
           query=importQuery.COUNTPREFERITI+event.idListaPreferiti;
        break;
      case 'ContaMiPiaceDaVedere':
           query=importQuery.COUNTDAVEDERE+event.idListaDaVedere;
           break;
      case 'idListaDaVedere':
           query=importQuery.ID_DV+event.idListaDaVedere;
         break;
      case 'idListaPreferiti':
           query=importQuery.ID_P+event.idListaPreferiti;
        break;

   }
   console.log(query);
    
    connection.query(query, function (error, results, fields) {
      
      connection.release();
      
      if (error) callback(error);
      
      

      else callback(null,{ 
              statusCode:200,
              body : results,
              headers : {'Content-Type': 'application/json'}
    });
  });
})};