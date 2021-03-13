package models.user

import org.unq.ui.model.User
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class CreateUserModel(instagramSystem: InstagramSystem) : UserModel(instagramSystem){

    override fun checkPassword() {
        if (passwordNew == "") {
            throw UserException("Se necesita una contraseña")
        }
        if(passwordConfirm == ""){
            throw UserException("Se necesita que repita la contraseña")
        }
        correctPass()
        checkSizePassword()
    }

    fun createUser(): User? {
        checkName()
        checkEmail()
        checkPassword()
        correctPass()
        var user: User? = null
        kotlin.runCatching {
            user = instagramSystem.register(name, email, passwordNew, image)
        }.onFailure {
            throw UserException("Ya existe un usuario para ese email")
        }
        return user
    }
}