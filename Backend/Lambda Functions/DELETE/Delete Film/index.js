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
   
    switch (event.filtro) {
      case 'idListaPreferiti':
           query=importQuery.DF1+"'"+event.idFilm+"'"+importQuery.DF2+event.idListaPreferiti;
        break;
      case 'idListaDaVedere':
           query=importQuery.DF1+"'"+event.idFilm+"'"+importQuery.DF3+event.idListaDaVedere;

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