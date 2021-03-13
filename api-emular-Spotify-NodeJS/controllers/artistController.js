'use strict'



const Artist = require('../models/artist');
const Album = require('../models/album');
const Song = require('../models/song');
const mongoosePaginate = require('mongoose-pagination');
const { restart } = require('nodemon');

const user = require('../models/user');
var path = require('path');
var fs = require('fs');


function getArtist(req, res) {

    var artistId = req.params.id;


    Artist.findById(artistId, (err, artist) => {
        if (err) {
            res.status(500).send({ message: 'failed' });
        } else {
            if (!artist) {
                res.status(404).send({ message: 'artist not found' });
            } else {
                res.status(200).send({ artist });
            }
        }
    });

};

function saveArtist(req, res) {
    var artist = new Artist();
    var params = req.body;

    artist.name = params.name;
    artist.description = params.description;
    artist.image = "";

    artist.save((err, artistStored) => {
        if (err) {
            res.status(500).send({ message: "failed trying to save artist" });
        } else {
            if (!artistStored) {
                res.status(404).send({ message: 'Artist has not been saved' });
            } else {
                res.status(200).send({ artist: artistStored });
            }
        }
    });
}

function getArtists(req, res) {
    if (req.params.page) {
        var page = req.params.page;
    } else {
        var page = 1;
    }

    var itemsPerPage = 3;


    Artist.find().sort('name').paginate(page, itemsPerPage, (err, artists, total) => {
        if (err) {
            res.status(500).send({ message: "conecction error" });
        } else {
            if (!artists) {
                res.status(404).send({ message: 'there isn´t any artist' });
            } else {
                res.status(200).send({
                    total_items: total,
                    artists: artists
                });
            }
        }
    });
}

function updateArtist(req, res) {
    const artistId = req.params.id;
    var update = req.body;

    Artist.findByIdAndUpdate(artistId, update, (err, artistUpdated) => {
        if (err) {
            res.status(500).send({ message: "conecction error" });
        } else {
            if (!artistUpdated) {
                res.status(404).send({ message: 'artist didn´t update' });
            } else {
                res.status(200).send({ artist: artistUpdated })
            }
        }
    })

}

function deleteArtist(req, res) {
    const artistId = req.params.id;

    Artist.findByIdAndRemove(artistId, (err, artistRemoved) => {
        if (err) {
            res.status(500).send({ message: "conecction error" });
        } else {
            if (!artistRemoved) {
                res.status(404).send({ message: 'artist didn´t delete' });
            } else {
                Album.find({ artist: artistRemoved._id }).remove((err, albumRemoved) => {
                    if (err) {
                        res.status(500).send({ message: "conecction error" });
                    } else {
                        if (!albumRemoved) {
                            res.status(404).send({ message: 'album didn´t delete' });
                        } else {
                            Song.find({ artist: albumRemoved._id }).remove((err, songRemoved) => {
                                if (err) {
                                    res.status(500).send({ message: "conecction error" });
                                } else {
                                    if (!songRemoved) {
                                        res.status(404).send({ message: 'song didn´t delete' });
                                    } else {
                                        res.status(200).send({ artist: artistRemoved })
                                    }
                                }
                            });
                        }

                    }
                })
            }
        }
    })
}


function uploadImage(req, res) {
    var artistId = req.params.id;
    var file_name = 'No file...'

    if (req.files) {
        console.log(req.files)
        var file_path = req.files.image.path;
        var file_split = file_path.split('\\');
        var file_name = file_split[2];

        var ext_split = file_name.split('\.');
        var file_ext = ext_split[1];

        if (file_ext == 'png' || file_ext == 'jpeg' || file_ext == 'jpg') {
            artist.findByIdAndUpdate(artistId, { image: file_name }, (err, artistUpdated) => {
                if (!artistUpdated) {
                    res.status(404).send({ message: 'error updating user' });
                } else {
                    res.status(200).send({ artist: artistUpdated });
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
    var path_file = './uploads/artistsUploads/' + imageFile;

    fs.access(path_file, fs.constants.F_OK, (err) => {
        if (!err) {
            res.sendFile(path.resolve(path_file));
        } else {
            res.status(200).send({ message: 'No existe la imagen' });
        }

    });
}

module.exports = {
    getArtist, saveArtist, getArtists, updateArtist, deleteArtist, uploadImage, getImageFile
};
