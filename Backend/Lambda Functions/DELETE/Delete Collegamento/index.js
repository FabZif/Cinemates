var mysql = require('mysql');
var config = require('./config.json');
var importQuery = require('./query.json');
var filter;
var filter2;
var t1;
var t2;
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
   
   
   filter=event.idAmico;
   filter2=event.idUtente;
   t1=importQuery.DC+filter+importQuery.DC2+filter2+";";
   t2=importQuery.DC+filter2+importQuery.DC2+filter;    //ELIMINAZIONE COMPLEMENTARE
   query=t1+t2;

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