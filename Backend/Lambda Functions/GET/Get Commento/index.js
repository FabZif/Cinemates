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
      case 'idUtente':
        query=importQuery.IdUtente;
        filter=event.valore;
      break;
    
      case 'idListaPreferiti':
        query=importQuery.IdPreferiti;
        filter=event.valore;
      break;
    
      case 'idListaDaVedere':
          query=importQuery.IdDaVedere;
          filter=event.valore;
      break;
      
      case 'CountCommentiPreferiti':
        query=importQuery.COUNTPreferiti;
        filter=event.valore;
        break;
        
      case 'CountCommentiDaVedere':
        query=importQuery.COUNTDaVedere;
        filter=event.valore;
        break;

   }
   console.log(query);
    
    connection.query(query+filter, function (error, results, fields) {
      
      connection.release();
      
      if (error) callback(error);
      
      

      else callback(null,{ 
              statusCode:200,
              body : results,
              headers : {'Content-Type': 'application/json'}
    });
  });
})};