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
  
   function escapeRegExp(string) {
  return string.replace(/[.*+?'${}()|[\]\\]/g, '\\$&'); // $& means the whole matched string
}

  exports.handler =  (event, context, callback) => { 

  context.callbackWaitsForEmptyEventLoop = false;
  pool.getConnection(function(err, connection) { 
     
  if(err) console.log("Error");
   
   
     switch (event.filtro) {
      
      case 'notificheApp':
           query=importQuery.IN;
           filter="( '"+event.Tipo+"','"+event.idDestinatario+"','"+event.Data+"','"+event.idMittente+"')";
           query+=filter;
        break;
      case 'notificheAdmin':
          var Testo = event.Testo;
          Testo= escapeRegExp(Testo);
           query=importQuery.INJ;
           filter="( '"+event.Tipo+"','"+event.idDestinatario+"','"+event.Data+"','"+event.idMittente+"'"+",'"+Testo+"')";
           query+=filter;
           
         break;
   }
   
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