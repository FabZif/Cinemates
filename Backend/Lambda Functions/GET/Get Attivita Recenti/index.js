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

  exports.handler =  (event, context, callback) => { 

  context.callbackWaitsForEmptyEventLoop = false;
  pool.getConnection(function(err, connection) { 
     
  if(err) console.log("Error");
  
 var Query= importQuery.Q1+event.idUtente+"; ";
 Query+= importQuery.Q2+event.idUtente+"; ";
 Query+= importQuery.Q3+event.idUtente+";";
   
  
  
    connection.query(Query, function (error, results, fields) {
      
      connection.release();
      
      if (error) callback(error);
      
      

      else callback(null,{ 
              statusCode:200,
              body : results,
              headers : {'Content-Type': 'application/json'}
    });
  });
})};