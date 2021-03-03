'use strict'

var express = require('express');
var userController = require('../controllers/userController');
var md_auth= require('../middlewares/authenticated');
var api = express.Router();
var multipart = require('connect-multiparty');
var md_upload = multipart({uploadDir: './uploads/userUploads'});


api.get('/testingUserController',md_auth.ensureAuth , userController.pruebas);
api.post('/register', userController.saveUser);
api.post('/login', userController.loginUSer);
api.put('/update-user/:id', md_auth.ensureAuth, userController.updateUser);
api.post('/upload-image-user/:id', [md_auth.ensureAuth, md_upload], userController.uploadImage);
api.get('/get-image-user/:imageFile', userController.getImageFile);

module.exports = api;