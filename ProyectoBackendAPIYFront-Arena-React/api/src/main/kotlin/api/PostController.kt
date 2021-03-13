package api

import dto.PostDTO
import io.javalin.http.Context
import org.api.model.BlankComment
import org.api.model.BlankPost
import org.api.model.ErrorResponse
import org.api.model.OkResponse
import org.unq.ui.model.DraftComment
import org.unq.ui.model.DraftPost
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import token.TokenController

class PostController(private val instagramSystem: InstagramSystem) {

    val tokenController = TokenController()

    fun getPost(ctx: Context) {
        val postId = ctx.pathParam("postId")                                                    //Se guarda el id del post a buscar
        try {
            validateTokenAndGetUserId(ctx)                                                        //Se valida
            val post = instagramSystem.getPost(postId)                                               //Se guarda el post si es encontrado, caso contrario lanza la excepcion

            val postDraft = PostDTO(post)                                                                  //Se guarda el post como un DTO
            ctx.status(200).json(postDraft)
        } catch (e: NotFound){
            ctx.status(404).json(ErrorResponse("Not found post with id $postId"))
        }
    }

    fun addComment(ctx: Context) {
        val postId = ctx.pathParam("postId")                                                    //Se guarda el id del post a comentar
        try{
            handleComment(ctx)
            val userId = validateTokenAndGetUserId(ctx)                                     //Se decodifica el token
            val bodyComment = ctx.body<DraftComment>()                                         //Se guarda el comentario a agregar

            instagramSystem.addComment(postId, userId, bodyComment)                                        //Se agrega el comentario al post a comentar
            ctx.status(200).json(OkResponse())
         }
         catch (e : NotFound){
             ctx.status(404).json(ErrorResponse("Not found post with id $postId"))
         } catch (e: BlankComment) {
            ctx.status(404).json(ErrorResponse("el comentario no puede estar en blanco"))
        }
    }

    fun modifyLike(ctx: Context) {
        val postId = ctx.pathParam("postId")                                                   //Se guarda el id del post a comentar
        try{
            val userId = validateTokenAndGetUserId(ctx)                                     //Se decodifica el token

            instagramSystem.updateLike(postId, userId)                                                   //Agrega o elimina el like del usuario actual en el post deseado
            ctx.status(200).json(OkResponse())
        }
        catch (e: NotFound){
            ctx.status(404).json(ErrorResponse("Not found post with id $postId"))
        }
    }

    fun addPost(ctx: Context){
        try {
            handlePost(ctx)
            val userId = validateTokenAndGetUserId(ctx)
            val post= ctx.body<DraftPost>()

            instagramSystem.addPost(userId, post)
            ctx.status(200).json(OkResponse())
        } catch (e: NotFound){
            ctx.status(404).json(ErrorResponse("no se pudo agregar el post"))
        } catch (e: BlankPost) {
            ctx.status(404).json(ErrorResponse("no puede haber campos vacios"))
        }
    }


    private fun validateTokenAndGetUserId(ctx: Context): String {
        val token = ctx.header("Authorization")!!

        return tokenController.validateToken(token)
    }

    private fun handlePost(ctx: Context) {
        val post= ctx.body<DraftPost>()


        if(post.description.isBlank() || post.landscape.isBlank()) {
            throw BlankPost("no puede haber campos vacios")
        }
    }

    private fun handleComment(ctx: Context) {
        val bodyComment = ctx.body<DraftComment>()

        if(bodyComment.body.isBlank()) {
            throw BlankComment("el comentario no puede estar vacio")
        }
    }
}