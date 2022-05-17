var mysql = require('mysql');
var config = require('./config.json');
var importQuery = require('./query.json');
var pool  = mysql.createPool({
    host     : config.dbhost,
    user     : config.dbuser,
    password : config.dbpassword,
    database : config.dbname,
    port : config.dbport,
    multipleStatements : config.dbstatement
  });
  
  var query;

  exports.handler =  (event, context, callback) => { 

  context.callbackWaitsForEmptyEventLoop = false;
  pool.getConnection(function(err, connection) { 
     
  if(err) console.log("Error");
   
    
   switch (event.filtro) {
       case 'Sender':
           query = importQuery.SENDER+event.idMittente;
           break;
       case 'Receiver':
           query=importQuery.QxID+event.idDestinatario+";";
         query+=importQuery.QxIDA+event.idDestinatario;
           break;
       default:
           // code
   }
   
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