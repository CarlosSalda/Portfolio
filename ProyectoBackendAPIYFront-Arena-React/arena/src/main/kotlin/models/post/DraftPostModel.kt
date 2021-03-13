package models.post

import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class DraftPostModel() {

    fun checkEmpty() {
        if(portrait == ""){
            throw UserException("Ingrese un portrait valido")
        }
        if(landScape == ""){
            throw UserException("Ingrese un landscape valido")
        }
    }

    var description= ""
    var landScape = ""
    var portrait = ""
    var postID = ""


    constructor(userModel: PostModel) : this(){
        description = userModel.description
        landScape = userModel.landScape
        portrait = userModel.portrait
        postID = userModel.id
    }

}