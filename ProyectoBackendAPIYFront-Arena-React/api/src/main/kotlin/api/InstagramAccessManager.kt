package api

import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.api.model.InstagramRoles
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import token.NotValidToken
import token.TokenController

class InstagramAccessManager(private val instagramSystem: InstagramSystem): AccessManager {

    val tokenController = TokenController()

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
        val token = ctx.header("Authorization")
        when {
            roles.contains(InstagramRoles.ANYONE) -> handler.handle(ctx)
            token === null -> throw UnauthorizedResponse()
            roles.contains(InstagramRoles.USER) -> {
                try {
                    val userID= tokenController.validateToken(token)
                    instagramSystem.getUser(userID)
                    ctx.attribute("userID", userID)
                    handler.handle(ctx)
                } catch (e: NotValidToken) {
                    throw  UnauthorizedResponse("Not Valid Token")
                } catch (e: NotFound) {
                    throw  UnauthorizedResponse("User Not Found")
                }
            }
        }
    }
}