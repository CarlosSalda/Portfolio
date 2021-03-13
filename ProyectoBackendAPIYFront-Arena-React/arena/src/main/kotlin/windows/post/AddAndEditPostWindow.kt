package windows.post

import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import models.post.DraftPostModel
import models.mainPage.UserViewModel


class AddAndEditPostWindow(owner: WindowOwner, model: DraftPostModel, modelU: UserViewModel): Dialog<DraftPostModel>(owner, model) {

    var modelUsuario = modelU

    override fun createFormPanel(mainPanel: Panel?) {
        //title = "Edit or Add Post"
        Label(mainPanel) withText("Portrait")
        TextBox(mainPanel) with{
            width = 800
            bindTo("portrait")
        }

        Label(mainPanel) withText("Landscape")
        TextBox(mainPanel) with{
            width = 800
            bindTo("landScape")
        }

        Label(mainPanel) withText("Description")
        TextBox(mainPanel) with{
            KeyWordTextArea(mainPanel) with {
                width = 800
                height = 200
                bindTo("description")
            }
        }

        Button(mainPanel) with{
            caption = "Accept"
            onClick {
                modelObject.checkEmpty()
                modelUsuario.check= false
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
