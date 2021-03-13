package windows.mainPage

import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import org.uqbar.arena.kotlin.extensions.*
import org.unq.ui.model.User
import models.post.DraftPostModel
import models.post.PostModel
import models.mainPage.UserViewModel
import windows.post.AddAndEditPostWindow
import windows.post.DeleteWindow

class UserViewWindow(owner: WindowOwner, modelUsuario: UserViewModel): SimpleWindow<UserViewModel>(owner, modelUsuario) {
    var userActual: User = modelObject.u
    //var instagramSystem: InstagramSystem = modelObject.getSystem()

    override fun addActions(actionsPanel: Panel) {

        Button(actionsPanel) with {
            caption = "Add new post"
            onClick {
                val post = DraftPostModel()
                val view = AddAndEditPostWindow(this@UserViewWindow, post, modelObject)
                view.onAccept {
                    modelObject.addPost(post, userActual)
                }
                view.open()
            }
        }

        Button(actionsPanel) with {
            caption = "Edit post"
            bindEnabledTo("check")
            onClick {
                val post = DraftPostModel(modelObject.selected!!)
                val view = AddAndEditPostWindow(this@UserViewWindow, post, modelObject)
                view.onAccept {
                    modelObject.editPost(modelObject.selected!!.id, post)
                }
                modelObject.checkPost(userActual, post)
                view.open()
            }
        }

        Button(actionsPanel) with {
            caption = "Delete Post"
            bindEnabledTo("check")
            onClick {
                val post = DraftPostModel(modelObject.selected!!)
                val deleteWindow = DeleteWindow(this@UserViewWindow, modelObject.selected!!, modelObject)
                deleteWindow.onAccept {
                    modelObject.removePost(modelObject.selected!!.id)
                }
                modelObject.checkPost(userActual, post)
                deleteWindow.open()
            }
        }
    }

    override fun createFormPanel(mainPanel: Panel) {
        Panel(mainPanel) with {

            title = "${userActual.name} Instagram's Feed"
            setMinWidth(700)
            setMinHeight(400)

            Panel(mainPanel) with {
                asHorizontal()
                Label(it) with {text = "Id: "}
                Label(it) bindTo ("userId")
            }

            Panel(mainPanel) with {
                asHorizontal()
                Label(it) with {text = "Name: "}
                Label(it) bindTo ("userName")
            }

            Panel(mainPanel) with {
                asHorizontal()
                Label(it) with {text = "Email: "}
                Label(it) bindTo ("userEmail")
            }

            Panel(mainPanel) with{
                Button(it) with {
                    setMinWidth(300)
                    caption = "Edit Profile"
                    onClick {
                        thisWindow.modelObject.editProfile(thisWindow, userActual)
                        /*val model = EditUserModel(userActual, instagramSystem)
                        val view = EditUserWindow(this@UserViewWindow, model, userActual)
                        view.onAccept {
                            userActual = model.editar()!!
                            thisWindow.modelObject.updateProfile(userActual)

                            val model = UserViewModel(userActual, instagramSystem)
                            val mainView = UserViewWindow(thisWindow, model)
                            this@UserViewWindow.close()
                            mainView.open()
                        }
                        view.open()*/
                    }
                }
                Button(it) with {
                        caption = "Close Session"
                        onClick {thisWindow.modelObject.closeSession(thisWindow, userActual)}
                }
            }

            Label(mainPanel) with {
                text = "-----------------------------------------------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------------------------------------"
                alignLeft()
            }

            Panel(mainPanel) with {
                asHorizontal()
                Label(it) with { text = "Search" }

                TextBox(it) with {
                    bindTo("search")
                    setWidth(500)
                }

                Button(it) with {
                    caption = "Search"
                    onClick {
                        runCatching {
                            thisWindow.modelObject.search()
                        }.onFailure {
                            thisWindow.modelObject.searchExceptions()
                        }
                    }
                }
                Button(it) with {
                    caption = "Clear Search"
                    onClick {
                        thisWindow.modelObject.clearSearch()
                        }
                    }
                }

            table<PostModel>(mainPanel) {
                bindItemsTo("postList")
                bindSelectionTo("selected")
                visibleRows = 10
                column {
                    title = "#"
                    fixedSize = 60
                    bindContentsTo("id")
                }
                column {
                    title = "LandScape"
                    fixedSize = 100
                    bindContentsTo("landScape")
                }
                column {
                    title = "Portrait"
                    fixedSize = 100
                    bindContentsTo("portrait")
                }
                column {
                    title = "Description"
                    bindContentsTo("description")
                }
            }
        }
    }
}



