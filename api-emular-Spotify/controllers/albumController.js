'use strict'

const Artist = require('../models/artist');
const Album = require('../models/album');
const Song = require('../models/song');
const mongoosePaginate = require('mongoose-pagination');
const { restart } = require('nodemon');

const user = require('../models/user');
var path = require('path');
var fs = require('fs');
const artist = require('../models/artist');


function getAlbum(req, res) {

    var albumId = req.params.id;

    Album.findById(albumId).populate({path: 'artist'}).exec((err, album) => {
        if (err) {
            res.status(500).send({ message: 'connection failed' });
        } else {
            if (!album) {
                res.status(404).send({ message: 'album not found' });
            } else {
                res.status(200).send({ album });
            }
        }
    });
};

function saveAlbum(req, res){
    const album = new Album();

    var params= req.body;
    album.title = params.title;
    album.description = params.description;
    album.published = params.published;
    album.image = "";
    album.artist = params.artist;

    album.save((err, albumStored)=> {
        if(err) {
            res.status(500).send({ message: 'connection failed' });
        } else {
            if(!albumStored){
                res.status(404).send({ message: 'save failed' });
            } else {
                res.status(200).send({ album: albumStored});
            }
        }
    })
}

function getAlbums(req, res){
    const artistId= req.params.artist;
    
    if(!artistId) {
        var find= Album.find({}).sort('title');
    } else {    
        var find = Album.find({artist: artistId}).sort('published');
    }

    find.populate({path: 'artist'}).exec((err, albums) => {
        if(err) {
            res.status(500).send({ message: 'connection failed'});
        } else {
            if(!albums) {
                res.status(404).send({ message: 'albums list is empty'});
            } else {
                res.status(200).send({albums});
            }
        }
    });
}

function updateAlbum(req, res){
    const albumId= req.params.id;
    const update= req.body;

    Album.findByIdAndUpdate(albumId, update, (err, albumUpdated) => {
        if(err) {
            res.status(500).send({ message: 'connection failed'});
        } else {
            if(!albumUpdated) {
                res.status(404).send({ message: 'album not found'});
            } else {
                res.status(200).send({album: albumUpdated});
            }
        }
    });
}

function deleteAlbum(req, res) {
    const albumId = req.params.id;

    Album.findByIdAndRemove(albumId , (err, albumRemoved) => {
        if (err) {
            res.status(500).send({ message: "conecction error" });
        } else {
            if (!albumRemoved) {
                res.status(404).send({ message: 'album didn´t delete' });
            } else {
                Song.find({ album: albumRemoved._id }).remove((err, songRemoved) => {
                    if (err) {
                        res.status(500).send({ message: "conecction error" });
                    } else {
                        if (!songRemoved) {
                            res.status(404).send({ message: 'song didn´t delete' });
                        } else {
                            res.status(200).send({ album: albumRemoved })
                        }
                    }
                });
            }
        }
    })
}
function uploadImage(req, res) {
    var songId = req.params.id;
    var file_name = 'No file...'

    if (req.files) {
        var file_path = req.files.image.path;
        var file_split = file_path.split('\\');
        var file_name = file_split[2];

        var ext_split = file_name.split('\.');
        var file_ext = ext_split[1];

        if (file_ext == 'png' || file_ext == 'jpeg' || file_ext == 'jpg') {
            console.log(file_name)
            Song.findByIdAndUpdate(songId, {file: file_name }, {new: true}, (err, songUpdated) => {
                if(err) {
                    res.status(500).send({ message: 'connection failed'});
                } else {
                    if (!songUpdated) {
                        res.status(405).send({ message: 'error updating Album' });
                    } else {
                        res.status(200).send({ song: songUpdated });
                    }
                }

            });
        } else {
            res.status(400).send({ message: 'error uploading file' });
        }

    } else {
        res.status(400).send({ message: 'there is no upload image' });
    }
};

function getImageFile(req, res) {
    var songFile = req.params.imageFile;
    var path_file = './uploads/albumsUploads/' + songFile;

    fs.access(path_file, fs.constants.F_OK, (err) => {
        if (!err) {
            res.sendFile(path.resolve(path_file));
        } else {
            res.status(200).send({ message: 'No existe la imagen' });
        }

    });
}

module.exports = {
    getAlbum, saveAlbum, getAlbums, updateAlbum, deleteAlbum, uploadImage, getImageFile
}