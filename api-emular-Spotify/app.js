'use strict'

var express = require('express');
var bodyParser = require('body-parser');
const { model } = require('mongoose');

var app = express();

//routes

const user_routes = require('./routes/userRoute');
const artist_routes= require('./routes/artistRoute');
const album_routes= require('./routes/albumRoute');
const song_routes= require('./routes/songRoutes');

app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

//http headers
app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Authorization, X-API-KEY, Origin , X-Requested-With, Content-Type, Accept, Acces-Control-Allow-Request-Method');
    res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE');
    res.header('Allow', 'GET, POST, OPTIONS, PUT, DELETE');

    next();
})

//basic route
app.use('/api', user_routes);
app.use('/api', artist_routes);
app.use('/api', album_routes);
app.use('/api', song_routes);
//basic routes


module.exports = app;