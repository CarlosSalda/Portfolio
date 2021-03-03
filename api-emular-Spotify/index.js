'use strict'

var mongoose = require('mongoose');
var app = require ('./app');
var port = process.env.port || 3977;

mongoose.connect('mongodb://localhost:27017/charlyfy_db', (err, res) => {
    if(err) {
        throw err;
    } else {
        console.log('the db connection is working...');
        
        app.listen(port, function(){
            console.log("api rest server listening in http://localhost:" + port)
        }) 
    }
});