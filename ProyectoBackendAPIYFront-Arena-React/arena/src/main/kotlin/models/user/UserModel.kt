package models.user

import models.mainPage.WelcomeToInstagramModel
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.exceptions.UserException

abstract class UserModel(instagramSystem: InstagramSystem): WelcomeToInstagramModel(instagramSystem) {

    open var name = ""
    open var email = ""
    open var passwordNew = ""
    var passwordConfirm = ""
    var image = ""

    fun checkName(){
        if(name == "") {
            throw UserException("Se necesita un nombre")
        }
        else if(!name.chars().allMatch(Character::isLetter)){
            throw UserException("Ingrese el campo sin numeros o simbolos")
        }
    }

    fun checkEmail() {
        if (email == "") {
            throw UserException("Se necesita un email")
        }
        if(!email.contains("@")){
            throw UserException("Ingrese un email valido")
        }
    }

    fun correctPass(){
        if(passwordNew != passwordConfirm) {
            throw UserException("La contraseña y su repeticion no coinciden entre si")
        }
    }

    fun checkSizePassword() {
        if(passwordConfirm.length <= 3 || passwordNew.length <= 3) {
            throw UserException("La contraseña debe ser de al menos 4 caracteres")
        }
    }

    /*private fun checkImg(){
        if(image == ""){
            Implementacionpendiente, que pida confirmacion para crear usuario sin imagen
        }
    }*/

    abstract fun checkPassword()
}
