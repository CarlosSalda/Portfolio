package windows.user

import models.user.EditUserModel
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import org.uqbar.arena.kotlin.extensions.*

class ChangePasswordWindow(owner: WindowOwner, model: EditUserModel) : Dialog<EditUserModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {
        Label(mainPanel) withText ("Contraseña actual")
        PasswordField(mainPanel) with {
            bindTo("passwordEditWindow")
        }

        Label(mainPanel) withText ("Contraseña nueva")
        PasswordField(mainPanel) with {
            bindTo("passwordNew")
        }

        Label(mainPanel) withText ("Repetir contraseña nueva")
        PasswordField(mainPanel) with {
            bindTo("passwordConfirm")
        }

        Button(mainPanel) with {
            caption = "Accept"
            onClick {
                modelObject.checkPassword()
                accept()
            }
        }

        Button(mainPanel) with {
            caption = "Cancel"
            onClick {
                modelObject.resetPasswords()
                cancel()
            }
        }

        modelObject.resetPasswords()
        close()
    }
}