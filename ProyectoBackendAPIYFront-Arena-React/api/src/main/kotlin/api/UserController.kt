package org.api

import dto.*
import io.javalin.http.Context
import org.api.model.*
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.UsedEmail
import token.TokenController

class UserController(private val instagramSystem: InstagramSystem) {

    val tokenController = TokenController()

    fun register(ctx: Context){

        val userRegister = ctx.body<UserRegisterDTO>()
            try {
                handleRegister(ctx)
                val user = instagramSystem.register(userRegister.name, userRegister.email, userRegister.password, userRegister.image)
                ctx.header("Authorization", tokenController.generateToken(user))
                ctx.header("UserID", user.id)
                ctx.status(201).json(OkResponse())
            } catch (e: UsedEmail) {
                ctx.status(400).json(ErrorResponse("Email already used"))
            } catch (e: BlankRegisterData) {
                ctx.status(400).json(ErrorResponseOnlyResult(e.result, "No puede haber campos en blanco"))
            } catch (e: NotValidName) {
                ctx.status(400).json(ErrorResponseOnlyResult(e.result, "A name can only be letters"))
            } catch(e: PasswordShort ) {
                ctx.status(400).json(ErrorResponseOnlyResult(e.result, "La contraseña debe de ser al menos 7 caracteres"))
            } catch (e: NotValidEmail) {
                ctx.status(400).json(ErrorResponseOnlyResult(e.result, "Ingrese Un Mail Valido"))
            }
    }

    fun login(ctx: Context) {
        val userLogin = ctx.body<UserLoginDTO>()
        if(userLogin.password.isBlank() || userLogin.email.isBlank()) {
            ctx.status(404).json(ErrorResponse("No puede haber campos en blanco"))
        } else {
            try {
                val user = instagramSystem.login(userLogin.email, userLogin.password)

                ctx.header("Authorization", tokenController.generateToken(user))
                ctx.header("UserID", user.id)
                ctx.status(200).json(OkResponse())
            } catch (e: NotFound) {
                ctx.status(404).json(ErrorResponse("El usuario no fue encontrado"))
            }
        }
    }

    fun timeLine(ctx: Context){
        try {
            val userLoggedId = validateTokenAndGetUserId(ctx)            //Se decodifica el token
            val user = instagramSystem.getUser(userLoggedId)                       //Se guarda el user obtenido con el token
            val userDTO = UserLoggedDTOUserPath(user, instagramSystem)                //Se crea el data travel object del usuario actual con sus followers y su timeline
            ctx.status(200).json(userDTO)
        } catch (e: NotFound) {
            ctx.status(401).json(ErrorResponse("User not found"))
        }

    }

    fun userInfoById(ctx: Context) {
        val userId = ctx.pathParam("userID")                               //Se guada el id del usuario deseado
        try {

            validateTokenAndGetUserId(ctx)                       //Se valida el token
            val userWanted = instagramSystem.getUser(userId)                     //Se obtiene el usuario deseado a traves del id guardado

            val userDTO = UserWantedDTO(userWanted, instagramSystem)                  //se crea el DTO del usuario deseado con sus followers y sus posts
            ctx.status(200).json(userDTO)
        } catch (e: NotFound) {
            ctx.status(404).json(ErrorResponse("Not found user with id $userId"))
        }
    }

    fun modifyFollow(ctx: Context) {
        try {
            val userLoggedId =validateTokenAndGetUserId(ctx)                 //Se decodifica el token y guarda su id
            val userLogged = instagramSystem.getUser(userLoggedId)                      //Se obtiene el usuario actual
            val userWanted = instagramSystem.getUser(ctx.pathParam("userID"))       //Se obtiene el usuario deseado
            instagramSystem.updateFollower( userLogged.id, userWanted.id)

            ctx.status(200).json(OkResponse())

        } catch (e: NotFound) {
            ctx.status(404).json(ErrorResponseOnlyResult("NotFound", "Not found user with id ${ctx.pathParam("userID")}"))
        }
    }

    fun search(ctx: Context){
       val searchParameter = ctx.queryParam("q")!!                                                                 //Se guarda el parametro de busqueda

        val token = ctx.header("Authorization")!!                                                               //Se guarda el token
        tokenController.validateToken(token)

        try {
            if(searchParameter.startsWith("#")) {                                                                      //Chequea si el parametro de busqueda es un tag
                val resultTag = ResultSearchByTagDTO(instagramSystem.searchByTag(searchParameter))                           //Se guarda la busqueda por tag a traves del DTO
                notFoundResult(resultTag.content)
                ctx.status(200).json(resultTag)
            } else {
                val resultUser = ResultSearchByUser(instagramSystem.searchByName(searchParameter), instagramSystem)          //Se guarda la busqueda por username si el parametro de busqueda no es un tag a traves de otro DTO
                notFoundResult(resultUser.content)
                ctx.status(200).json(resultUser)
            }
        } catch (e : NotFound){
            ctx.status(404).json(ErrorResponseOnlyResult("NotFound", "Not found data with $searchParameter"))
        }
    }

    private fun validateTokenAndGetUserId(ctx: Context): String {
        val token = ctx.header("Authorization")!!

        return tokenController.validateToken(token)
    }

    private fun handleRegister(ctx: Context) {
        val userRegister = ctx.body<UserRegisterDTO>()

        if(userRegister.name.isBlank() || userRegister.password.isBlank() || userRegister.email.isBlank()) {
            throw BlankRegisterData("Ninguno de los campos no pueden estar en Blanco")
        }
        if(!userRegister.email.contains("@") || !userRegister.email.contains(".com")) {
            throw NotValidEmail("ingrese un mail valido")
        }
        if(!userRegister.name.chars().allMatch(Character::isLetter)){
            throw NotValidName("Ingrese el campo sin numeros o simbolos")
        }
        if(userRegister.password.length <= 7  && userRegister.password.isNotBlank()) {
            throw PasswordShort("la contraseña debe de ser al menos 7 caracteres")
        }
    }

    private fun <T> notFoundResult(content: List<T>){
        if(content.isEmpty()){
            throw NotFound("Not found data")
        }
    }
}
