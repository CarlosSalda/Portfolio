package windows.mainPage

import models.mainPage.*
import models.user.LoginModel
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import org.uqbar.arena.kotlin.extensions.*
import windows.user.LoginWindow

class WelcomeToInstagramWindow(owner: WindowOwner, model: WelcomeToInstagramModel) : Dialog<WelcomeToInstagramModel>(owner, model){

    //ventana de bienvenida, la primera que se refleja cuando iniciamos

    override fun createFormPanel(mainPanel: Panel?) {
        val instagramSystem = modelObject.getSystem()
        var model : WelcomeToInstagramModel

        // dos botones de uno encargado del login, otro del registro para el sistema.
        title = "Instagram"
        Label(mainPanel) withText ("BIENVENIDX A INSTAGRAM")

        Label(mainPanel) withText ("Que deseas hacer?")

        Button(mainPanel) with {
            caption = "Login"
            onClick {
                modelObject.login(thisWindow)
            }
        }

        Button(mainPanel) with {
            caption = "Create User"
            onClick {
                modelObject.register(thisWindow)/*
                val model = CreateUserModel(instagramSystem)
                val view = CreateUserWindow(thisWindow, model)  // se crea la ventana de registro, que es la que vemos ni bien logueamos correctamente
                view.onAccept{
                    val user = (model).createUser()
                    thisWindow.close()
                    UserViewWindow(thisWindow, UserViewModel(user!!, instagramSystem)).open() //se abre la ventana principal del sistema, si se cumplen las condiciones previas
                }
                view.open()*/
            }
        }
    }
}

//main para comenzar con la APP
fun main() = InstagramApp().start()