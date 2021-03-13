package models.user

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class EditUserModel(user: User, instagramSystem: InstagramSystem): UserModel(instagramSystem){
    val id = user.id
    var img = user.image
    override var name = user.name
    override var email= user.email
    var passOriginal = user.password
    var password = ""

    var passwordEditWindow = ""

    fun editar(): User {
        this.checkName()
        return if (passwordNew == ""){
            instagramSystem.editProfile(id, name, passOriginal, img)
        } else{
            editarConPass()
        }
    }

    fun editarConPass(): User {
        notAuthorization()
        correctPass()
        val user: User?
        if(passwordNew == passwordConfirm){
            user = instagramSystem.editProfile(id, name, passwordNew, img)
        } else{
            throw UserException("Las contraseñas nuevas no coinciden entre si")
        }
        return user
    }

    fun notAuthorization() {
        if(password.isEmpty()){
            throw  UserException("El campo de contraseña no puede estar vacio")
        }
        if(passOriginal != password) {
            throw UserException("La contraseña no es correcta")
        }
    }

    fun editarConPassEditWindow(): User {

        val user: User?
        if(passwordNew == passwordConfirm){
            user = instagramSystem.editProfile(id, name, passwordNew, img)
        } else{
            throw UserException("Las contraseñas nuevas no coinciden entre si")
        }
        return user
    }

    fun resetPasswords() {
        passwordNew = ""
        passwordConfirm = ""
        passwordEditWindow = ""
    }

    override fun checkPassword() {
        if(passwordNew.isEmpty() || passwordConfirm.isEmpty() || passwordEditWindow.isEmpty()){
            throw UserException("Ningun campo puede estar vacio")
        }
        if(passwordEditWindow != passOriginal){
            throw UserException("La contraseña actual no es la correcta")
        }
        this.checkSizePassword()
        if(passwordEditWindow == passOriginal){
            editarConPassEditWindow()
            passOriginal = passwordNew
        }
    }
}