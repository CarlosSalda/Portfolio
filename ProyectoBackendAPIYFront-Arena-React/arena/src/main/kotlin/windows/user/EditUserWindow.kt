package windows.user

import models.user.EditUserModel
import org.unq.ui.model.User
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class EditUserWindow(owner: WindowOwner, model: EditUserModel, user: User) : Dialog<EditUserModel>(owner, model) {

    var userInsta= user

    override fun createFormPanel(mainPanel: Panel?) {

        title = "Edit ${userInsta.name}'s Profile"
        Label(mainPanel) withText ("Name")
        TextBox(mainPanel) with {
            bindTo("name")
        }

        Label(mainPanel) withText ("Image")
        TextBox(mainPanel) with {
            bindTo("img")
        }

        Button(mainPanel) with {
            caption = "Cambiar contraseña"

            onClick {
                val view = ChangePasswordWindow(this@EditUserWindow, modelObject)
                view.onAccept {
                    modelObject.resetPasswords()
                }
                view.open()
            }
        }

        Panel(mainPanel) with{
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick {
                    if(thisWindow.modelObject.img == userInsta.image && thisWindow.modelObject.name == userInsta.name) {
                        accept()
                    } else {
                        //thisWindow.modelObject.notAuthorization(userInsta)
                        accept()
                    }
                }
            }

            Button(it) with {
                caption = "Cancel"
                onClick {
                    cancel()
                }
            }
        }
    }
}