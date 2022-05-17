var mysql = require('mysql');
var config = require('./config.json');
var importQuery = require('./query.json');
var Query;


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
    case 'InComunePreferiti':
        Query = importQuery.Comune1Preferiti+event.idUtente+importQuery.Comune2Preferiti+event.idAmico+")";
      break;
    case 'PersonalePreferiti':
        Query = importQuery.FP+event.idUtente;
      break;
    case 'InComuneDaVedere':
       Query = importQuery.Comune1DaVedere+event.idUtente+importQuery.Comune2DaVedere+event.idAmico+")";
       break;
    case 'PersonaleDaVedere':
              Query = importQuery.FDV+event.idUtente;
          break;

      
    
    default:
      // code
  }
   
  
  
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