'use strict'

const jwt = require('../services/jwt');
const bcrypt = require('bcrypt-nodejs');
const user = require('../models/user');
const { param } = require('../routes/userRoute');
const { use } = require('../app');
const fs = require('fs');
const path = require('path')



function pruebas(req, res) {
    res.status(200).send({
        message: 'testing user controller'
    });
}

function saveUser(req, res) {
    var userAPP = new user();

    var params = req.body;

    console.log(params);

    userAPP.name = params.name;
    userAPP.surname = params.surname;
    userAPP.email = params.email;
    userAPP.role = 'ROLE_USER';
    userAPP.image = "";

    if (params.password) {
        //encrypt pass
        bcrypt.hash(params.password, null, null, function (err, hash) {
            userAPP.password = hash;
            if (userAPP.name != null, userAPP.surname != null, userAPP.email != null) {
                userAPP.save((err, userStored) => {
                    if (err) {
                        res.status(500).send({ message: "failed saving user" })
                    } else {
                        if (!userStored) {
                            res.status(500).send({ message: "failed in registing user" })
                        } else {
                            res.status(200).send({ user: userStored })
                        }
                    }
                })
            } else {
                res.status(400).send({ message: "fill all camps" })
            }
        })
    } else {
        res.status(500).send({ message: "put your password" })
    }
}


function loginUSer(req, res) {
    var params = req.body;

    var emailU = params.email;
    var passwordU = params.password;

    user.findOne({ email: emailU.toLowerCase() }, (err, user) => {
        if (err) {
            res.status(500).send({ message: 'Error en la peticion' });
        } else {
            if (!user) {
                res.status(400).send({ message: 'User not found' });
            } else {
                bcrypt.compare(passwordU, user.password, function (err, check) {
                    if (check) {
                        //token must go here
                            res.status(200).send({
                                token: jwt.createToken(user)
                            })
                        } else {
                            res.status(400).send({ message: 'Login error check inputs' });
                        }
                });
            }
        }
    });

}

function updateUser(req, res) {
    var userId = req.params.id;
    var update = req.body;

    user.findByIdAndUpdate(userId, update, (err, userUpdated) => {
        if (err) {
            res.status(500).send({ message: 'error updating user' });
        } else {
            if (!userUpdated) {
                res.status(404).send({ message: "couldn't update user" });
            } else {
                res.status(200).send({ user: userUpdated });
            }
        }
    })
};

function uploadImage(req, res) {
    var userId = req.params.id;
    var file_name = 'No file...'

    if (req.files) {
        var file_path = req.files.image.path;
        var file_split = file_path.split('\\');
        var file_name = file_split[2];

        var ext_split = file_name.split('\.');
        var file_ext = ext_split[1];

        if (file_ext == 'png' || file_ext == 'jpeg' || file_ext == 'jpg') {
            user.findByIdAndUpdate(userId, { image: file_name }, (err, userUpdated) => {
                if (!userUpdated) {
                    res.status(404).send({ message: 'error updating user' });
                } else {
                    res.status(200).send({ image: file_name, user: userUpdated });
                }
            });
        } else {
            res.status(200).send({ message: 'error uploading image' });
        }

        console.log(file_path);
    } else {
        res.status(200).send({ message: 'there is no upload image' });
    }
};

function getImageFile(req, res) {
    var imageFile = req.params.imageFile;
    var path_file = './uploads/users/' + imageFile;

    fs.access(path_file, fs.constants.F_OK, (err) => {
        if (!err) {
            res.sendFile(path.resolve(path_file));
        } else {
            res.status(200).send({ message: 'No existe la imagen' });
        }

    });
}

module.exports = {
    pruebas, saveUser, loginUSer, updateUser, uploadImage, getImageFile
};