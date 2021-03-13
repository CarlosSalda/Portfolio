package models.mainPage

import models.post.DraftPostModel
import models.post.PostModel
import models.user.CloseSessionModel
import models.user.EditUserModel
import org.unq.ui.model.*
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import windows.mainPage.UserViewWindow
import windows.mainPage.WelcomeToInstagramWindow
import windows.user.CloseSessionWindow
import windows.user.EditUserWindow

@Observable
class UserViewModel(user: User, instagramSystem: InstagramSystem) : WelcomeToInstagramModel(instagramSystem) {
    var u:User = user
    val userId = u.id
    val userEmail = u.email
    var userName = u.name
    var userPass = u.password
    var userImg = u.image
    var selected: PostModel? = null
        set(value) {
            check= true
            field = value
        }
    var search=""
    var check = false
    lateinit var postList: List<PostModel>

    init {
        updatePosts()
    }

    fun updatePosts() {
        postList= instagramSystem.searchByUserId(userId).map { PostModel(it.id, it.landscape, it.portrait, it.description) }
    }

    fun updateProfile(user: User) {
        u = instagramSystem.editProfile(user.id, user.name, user.password, user.image)
        userName = u.name
        userPass = u.password
        userImg = u.image
    }

    fun editPost(postId: String, post: DraftPostModel) {
        instagramSystem.editPost(postId, DraftPost(post.portrait, post.landScape, post.description))
        updatePosts()
        check = false
    }

    fun addPost(post: DraftPostModel, user: User) {
        instagramSystem.addPost(user.id, DraftPost(post.portrait, post.landScape, post.description))
        updatePosts()
        check = false
    }

    fun removePost(postId: String) {
        instagramSystem.deletePost(postId)
        updatePosts()
        check = false
    }

    fun search() {
        val postRequested = mutableListOf<PostModel>()
        val requestedDescription = search.toRegex()
        for (post in instagramSystem.posts){
            if(requestedDescription.containsMatchIn(post.id) || requestedDescription.containsMatchIn(post.description)) {
                postRequested.add(PostModel(post.id, post.portrait, post.landscape, post.description))
            }
        }
        postList = postRequested
    }

    fun clearSearch() {
        updatePosts()
        search = ""
    }

    fun searchExceptions() {
        throw UserException("No fue encontrado un post con: $search")
    }

    fun checkPost(userActual: User, post: DraftPostModel) {
        val idDuenio = instagramSystem.getPost(post.postID).user.id
        if (idDuenio != userActual.id){
            throw UserException("El post no es de su propiedad")
        }
    }

    fun editProfile(thisWindow: UserViewWindow, userActual: User) {
        val model = EditUserModel(userActual, instagramSystem)
        val view = EditUserWindow(thisWindow, model, userActual)
        view.onAccept {
            val userActualizado = model.editar()
            this.updateProfile(userActual)

            val mainView = UserViewWindow(thisWindow, UserViewModel(userActualizado, instagramSystem))
            thisWindow.close()
            mainView.open()
        }
        view.open()
    }

    fun closeSession(thisWindow: UserViewWindow, userActual: User) {
        val model = CloseSessionModel(userActual)
        val closeSessionWindow = CloseSessionWindow(thisWindow, model)
        closeSessionWindow.onAccept {
            /*var model = UserViewModel(userActual)
            val mainView = UserViewWindow(thisWindow, model, userActual)
            this@UserViewWindow.close()
            mainView.open()*/
            thisWindow.close()
            val welcome = WelcomeToInstagramWindow(thisWindow, WelcomeToInstagramModel(instagramSystem))
            welcome.open()
        }
        closeSessionWindow.open()
    }
}