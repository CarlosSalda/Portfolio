'use strict'

var mongoose = require('mongoose');
var schema = mongoose.Schema;

var albumSchema = schema({
    title: String,
    description: String,
    published: String,
    image: String,
    artist: {type: schema.ObjectId, ref: 'Artist'}
});

module.exports = mongoose.model('Album', albumSchema)