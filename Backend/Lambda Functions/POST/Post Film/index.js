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
  function escapeRegExp(string) {
  return string.replace(/[.*+?'${}()|[\]\\]/g, '\\$&'); // $& means the whole matched string
}

  exports.handler =  (event, context, callback) => { 

  context.callbackWaitsForEmptyEventLoop = false;
  pool.getConnection(function(err, connection) { 
     
  if(err) console.log("Error");
    var Titolo = event.Titolo;
          Titolo= escapeRegExp(Titolo);
          var Descrizione = event.Descrizione;
          Descrizione = escapeRegExp(Descrizione);
          console.log(Descrizione);
          var Immagine = event.Immagine;
          Immagine = escapeRegExp(Immagine);
          
       switch (event.filtro) {
      
         case 'idListaPreferiti':
           query=importQuery.IF+"( " +event.idListaPreferiti+","+"null"+","+event.idFilm+", '"+Titolo+"' , '"+Descrizione+"' , '"+Immagine+"' , '"+event.DataAggiunta+"' )";
        break;
         case 'idListaDaVedere':
           query=importQuery.IF+"( " +"null"+","+event.idListaDaVedere+","+event.idFilm+", '"+Titolo+"' , '"+Descrizione+"' , '"+Immagine+"' , '"+event.DataAggiunta+"' )";

         break;
   }
   
   
    connection.query(query, function (error, results, fields) {
      
      connection.release();
      
      if (error) callback(null,{ 
              statusCode:400,
              body : "SI E' VERIFICATO UN ERRORE",
              headers : {'Content-Type': 'application/json'}
    });
      
      

      else callback(null,{ 
              statusCode:200,
              body : "INSERITO CORRETTAMENTE",
              headers : {'Content-Type': 'application/json'}
    });
  });
})};