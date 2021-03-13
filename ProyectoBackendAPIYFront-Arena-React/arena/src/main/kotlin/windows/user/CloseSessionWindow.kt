package windows.user

import models.user.CloseSessionModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class CloseSessionWindow(owner: WindowOwner, model: CloseSessionModel): Dialog<CloseSessionModel>(owner, model){
    override fun createFormPanel(mainPanel: Panel?) {
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {text = "Estas seguro de querer cerrar la sesion de: "}
            Label(it) bindTo ("name")
            Label(it) with {text = "?"}
        }

        Button(mainPanel) with{
            caption = "Accept"
            onClick {
                accept()
            }
        }

        Button(mainPanel) with{
            caption = "Cancel"
            onClick {
                cancel()
            }
        }
    }
}