package models.mainPage

import models.user.CreateUserModel
import models.user.LoginModel
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable
import windows.mainPage.UserViewWindow
import windows.mainPage.WelcomeToInstagramWindow
import windows.user.CreateUserWindow
import windows.user.LoginWindow

@Observable
open class WelcomeToInstagramModel(val instagramSystem: InstagramSystem) {

    fun getSystem(): InstagramSystem {
        return instagramSystem
    }

    fun login(thisWindow: WelcomeToInstagramWindow) {
        thisWindow.close()
        LoginWindow(thisWindow, LoginModel(instagramSystem)).open()
    }

    fun register(thisWindow: WelcomeToInstagramWindow) {
        val model = CreateUserModel(instagramSystem)
        val view = CreateUserWindow(thisWindow, model)  // se crea la ventana de registro, que es la que vemos ni bien logueamos correctamente
        view.onAccept{
            val user = (model).createUser()
            thisWindow.close()
            UserViewWindow(thisWindow, UserViewModel(user!!, instagramSystem)).open() //se abre la ventana principal del sistema, si se cumplen las condiciones previas
        }
        view.open()
    }
}