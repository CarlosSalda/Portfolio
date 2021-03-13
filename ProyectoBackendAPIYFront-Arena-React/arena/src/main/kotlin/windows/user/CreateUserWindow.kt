package windows.user

import models.user.CreateUserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.kotlin.extensions.bindTo
import org.uqbar.arena.kotlin.extensions.caption
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.kotlin.extensions.withText
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class CreateUserWindow(owner: WindowOwner, model: CreateUserModel) : Dialog<CreateUserModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Create Profile"
        Label(mainPanel) withText ("Name")
        TextBox(mainPanel) with {
            width = 200
            bindTo("name")
        }

        Label(mainPanel) withText ("Image")
        TextBox(mainPanel) with {
            width = 200
            bindTo("image")
        }

        Label(mainPanel) withText ("Email")
        TextBox(mainPanel) with {
            width = 200
            bindTo("email")
        }

        Label(mainPanel) withText ("Contraseña nueva")
        PasswordField(mainPanel) with {
            width = 200
            bindTo("passwordNew")
        }

        Label(mainPanel) withText ("Repetir contraseña nueva")
        PasswordField(mainPanel) with {
            width = 200
            bindTo("passwordConfirm")
        }

        Button(mainPanel) with {
            caption = "Accept"
            onClick {
                //modelObject.correctPass()
                //modelObject.checkSizePassword()
                accept()
            }
        }

        Button(mainPanel) with {
            caption = "Cancel"
            onClick {
                cancel()
            }
        }
    }
}