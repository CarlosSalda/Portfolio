'use strict'

var jwt = require('jwt-simple');
var moment= require('moment');
const { render } = require('../app');

var secret= 'secret_password';

exports.ensureAuth = function(req, res, next) {
    if(!req.headers.authorization) {
        return res.status(403).send({message: 'error in authentication try again'});
    }

    var token = req.headers.authorization.replace(/['"]+/g, '');

    try {
        var payload = jwt.decode(token, secret);

        if(payload.exp <= moment().unix()){
            res.status(401).send({message: 'token expired'});
        }
    } catch(ex) {
       
        res.status(404).send({message: 'invalid token'});
    }

    req.user= payload;

    next();
};