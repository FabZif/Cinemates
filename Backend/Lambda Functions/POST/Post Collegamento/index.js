var mysql = require('mysql');
var config = require('./config.json');
var importQuery = require('./query.json');
var filter;
var filter2;
var query;


var pool  = mysql.createPool({
    host     : config.dbhost,
    user     : config.dbuser,
    password : config.dbpassword,
    database : config.dbname,
    port : config.dbport,
    multipleStatements:true
  });

  exports.handler =  (event, context, callback) => { 

  context.callbackWaitsForEmptyEventLoop = false;
  pool.getConnection(function(err, connection) { 
     
  if(err) console.log("Error");
   
   
    
    filter="("+event.idUtente+","+event.idAmico+");";
    filter2="("+event.idAmico+","+event.idUtente+")";
    query=importQuery.IV+filter+importQuery.IV+filter2;
    console.log(query);
    
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