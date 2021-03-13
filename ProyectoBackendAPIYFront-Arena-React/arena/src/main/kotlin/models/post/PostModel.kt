package models.post

import org.uqbar.commons.model.annotations.Observable

@Observable
class PostModel(var id: String, var portrait: String, var landScape:String, var description: String){}