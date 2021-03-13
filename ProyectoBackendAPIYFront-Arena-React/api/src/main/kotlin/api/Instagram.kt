import api.InstagramAccessManager
import api.PostController
import dto.UserRegisterDTO
import io.javalin.Javalin
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.BadRequestResponse
import org.api.UserController
import org.api.model.ErrorResponse
import org.api.model.MyResponse
import org.api.model.InstagramRoles
import org.unq.ui.bootstrap.getInstagramSystem

class Instagram {
    val instagramSystem = getInstagramSystem()
    val userController = UserController(instagramSystem)
    val postController = PostController(instagramSystem)

    fun start() {
        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/"))
            it.accessManager(InstagramAccessManager(instagramSystem))
            it.enableCorsForAllOrigins()
        }

        app.before {
            it.header("Access-Control-Expose-Headers", "*")
        }

        app.routes() {
            /*path("start") {
                get{ctx -> ctx.status(200).json(MyResponse("Welcome to Instagram"))}
            }*/
            path("Register"){
                post(userController::register, setOf(InstagramRoles.ANYONE))
            }
            path("Login") {
                post(userController::login, setOf(InstagramRoles.ANYONE))
            }
            path("user") {
                get(userController::timeLine, setOf(InstagramRoles.USER))
                path(":userID") {
                    get(userController::userInfoById, setOf(InstagramRoles.USER))
                    path("follow") {
                        put(userController::modifyFollow, setOf(InstagramRoles.USER))
                    }
                }
            }

            path("post") {
                path(":postId"){
                    get(postController::getPost, setOf(InstagramRoles.USER))
                    path("like") {
                        put(postController::modifyLike, setOf(InstagramRoles.USER))
                    }
                    path("comment") {
                        post(postController::addComment, setOf(InstagramRoles.USER))
                    }
                }
                path("add") {
                    post(postController::addPost, setOf(InstagramRoles.USER))
                }
            }

            path("search"){
                get(userController::search, setOf(InstagramRoles.USER))
            }
        }

        app.exception(BadRequestResponse::class.java) { e, ctx ->
            ctx.status(400).json(ErrorResponse("Bad Request"))}

        app.start(7000)
    }
}

fun main(args: Array<String>) {
    Instagram().start()
}