package windows.post

import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import org.uqbar.arena.kotlin.extensions.*
import models.post.PostModel
import models.mainPage.UserViewModel

class DeleteWindow(owner: WindowOwner, model: PostModel, modelUsuario: UserViewModel): Dialog<PostModel>(owner, model){

    var modelU= modelUsuario

    override fun createFormPanel(mainPanel: Panel?) {
        Label(mainPanel) with {
            text = "Estas seguro de querer borrar el post con id: ${modelObject.id}?"
        }

        Button(mainPanel) with{
            caption = "Accept"
            onClick {
                accept()
                modelU.check = false
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
