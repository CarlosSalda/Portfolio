package models.user

import models.mainPage.WelcomeToInstagramModel
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class LoginModel(instagramSystem: InstagramSystem) : WelcomeToInstagramModel(instagramSystem) {

    //creacion del loginModel

    var email= ""
    var password= ""

    fun loguear(): User {
        kotlin.runCatching {
            instagramSystem.login(email, password)
        }.onFailure {
            throw UserException("El email o la contraseña es incorrecta")
        }
        return instagramSystem.login(email, password)
    }
}