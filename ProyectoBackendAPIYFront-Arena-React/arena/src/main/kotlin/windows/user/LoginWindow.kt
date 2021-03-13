package windows.user

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import models.mainPage.UserViewModel
import models.mainPage.WelcomeToInstagramModel
import models.user.LoginModel
import windows.mainPage.UserViewWindow
import windows.mainPage.WelcomeToInstagramWindow

class LoginWindow(owner: WindowOwner, model: LoginModel) : SimpleWindow<LoginModel>(owner, model) {

    //se crea un panel, con los campos de email y contraseña necesarios para loguearse
    override fun createFormPanel(mainPanel: Panel?) {
        title = "User Login"

        Label(mainPanel) withText("E-mail")
        TextBox(mainPanel) with{
            width = 200
            bindTo("email")
        }

        Label(mainPanel) withText("Contraseña")
        PasswordField(mainPanel) with {
            width = 200
            bindTo("password")
        }
    }

    override fun addActions(mainPanel: Panel?) {
        //funcionamiento del Login
        // primero se handlea el loguin con su respectivo error, luego se guarda el model y por ultimo se crea la ventana con un owner y model correspondientes
        Button(mainPanel) with {
            alignCenter()
            caption = "Login"
            onClick {
                val user = modelObject.loguear()
                val model = UserViewModel(user, modelObject.getSystem())
                val mainView = UserViewWindow(this@LoginWindow, model)
                this@LoginWindow.close()
                mainView.open()
            }
        }
        Button(mainPanel) with {
            //solamente hace el funcionamiento de back
            alignCenter()
            caption = "Back"
            onClick {
                thisWindow.close()
                val model = WelcomeToInstagramModel(modelObject.getSystem())
                WelcomeToInstagramWindow(thisWindow, model).open()
            }
        }
    }
}