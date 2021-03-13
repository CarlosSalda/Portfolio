'use strict'

const Artist = require('../models/artist');
const Album = require('../models/album');
const Song = require('../models/song');
const mongoosePaginate = require('mongoose-pagination');
const { restart } = require('nodemon');

const user = require('../models/user');
var path = require('path');
var fs = require('fs');

function getSong(req, res) {
    const songId= req.params.id;

    Song.findById(songId).populate({path: 'album'}).exec((err, song) => {
        if(err) {
            res.status(500).send({ message: 'connection failed' });
        } else {
            if(!song){
                res.status(404).send({ message: 'song not found' });
            } else {
                res.status(200).send({song});
            }
        }
    })
}

function saveSong(req, res) {
    var song= new Song();

    var params = req.body;
    song.number = params.number;
    song.name= params.name;
    song.duration = params.duration;
    song.file = "";
    song.album = params.album;

    song.save((err, songStored) => {
        if(err) {
            res.status(500).send({ message: 'connection failed' });
        } else {
            if(!songStored){
                res.status(404).send({ message: 'save failed' });
            } else {
                res.status(200).send({ song: songStored});
            }
        }
    });
}

function getSongs(req, res) {
    const albumId = req.params.album;

    if(!albumId) {
        var find= Song.find({}).sort('number');
    } else {
        var find= Song.find({album: albumId}).sort('number');
    }

    find.populate({
        path: 'album',
        populate: {
            path: 'artist',
            model: 'Artist'
        }
    }).exec((err, songs) => {
            if(err) {
                res.status(500).send({ message: 'connection failed' });
            } else {
                if(!songs){
                    res.status(404).send({ message: 'songs not found' });
                } else {
                    res.status(200).send({ songs});
                }
            }
        })
}

function updateSong(req, res) {
    var songId = req.params.id;
    var update = req.body;

    Song.findByIdAndUpdate(songId, update, (err, songUpdated) => {
        if(err) {
            res.status(500).send({ message: 'connection failed' });
        } else {
            if(!songUpdated){
                res.status(404).send({ message: 'song not found' });
            } else {
                res.status(200).send({ song: songUpdated});
            }
        }
    })
}

function deleteSong(req, res) {
    var songId = req.params.id;

    Song.findByIdAndRemove(songId, (err, songRemoved) => {
        if(err) {
            res.status(500).send({ message: 'connection failed' });
        } else {
            if(!songRemoved){
                res.status(404).send({ message: 'song has not been deleted' });
            } else {
                res.status(200).send({ song: songRemoved});
            }
        }
    });
}



function uploadFile(req, res) {
    var songId = req.params.id;
    var file_name = 'No file...'

    if (req.files) {
        var file_path = req.files.file.path;
        var file_split = file_path.split('\\');
        var file_name = file_split[2];

        var ext_split = file_name.split('\.');
        var file_ext = ext_split[1];

        if (file_ext == 'mp3' || file_ext == 'mp4' || file_ext == 'ogg') {
            console.log(file_name)
            Song.findByIdAndUpdate(songId, {file: file_name }, {new: true}, (err, songUpdated) => {
                if(err) {
                    res.status(500).send({ message: 'connection failed'});
                } else {
                    if (!songUpdated) {
                        res.status(405).send({ message: 'error updating song' });
                    } else {
                        res.status(200).send({ song: songUpdated });
                    }
                }
            });
        } else {
            res.status(400).send({ message: 'error uploading song' });
        }

    } else {
        res.status(400).send({ message: 'there is no upload image' });
    }
};

function getSongFile(req, res) {
    var songFile = req.params.songFile;
    var path_file = './uploads/songsUploads/' + songFile;

    fs.access(path_file, fs.constants.F_OK, (err) => {
        if (!err) {
            res.sendFile(path.resolve(path_file));
        } else {
            res.status(200).send({ message: 'No existe el fichero de audio' });
        }

    });
}

module.exports = {
    saveSong, getSong, getSongs, updateSong, deleteSong, getSongFile, uploadFile
}