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

          query=importQuery.IP+"( '"+event.TestoCommento+"' ,'"+event.Data+"',"+event.idListaPreferiti+","+"null"+","+event.idUtente+")";
                      console.log(query);

        break;
      case 'idListaDaVedere':
          query=importQuery.IP+"( '"+event.TestoCommento+"','"+event.Data+"',"+"null"+","+event.idListaDaVedere+","+event.idUtente+")";
         break;
   }

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