package org.api.model

import io.javalin.core.security.Role

enum class InstagramRoles: Role {
    ANYONE, USER
}

data class MyResponse(val result: String)
data class OkResponse(val result: String = "OK")
data class ErrorResponseOnlyResult(var error: String, var result: String){}

data class ErrorResponse(private val mess: String){
    val result: String = "ERROR"
    val message = mess
}
class PasswordShort(string: String): Exception(string){
    val result: String = "PASSERROR"
    override val message = string
}
class NotValidEmail(string: String): Exception(string){
    val result: String = "EMAILERROR"
    override val message = string
}
class BlankRegisterData(string: String): Exception(string){
    val result: String = "DATAERROR"
    override val message = string
}
class BlankPost(string: String): Exception(string){
    val result: String = "POSTERROR"
    override val message = string
}
class BlankComment(string: String): Exception(string){
    val result: String = "COMMENTERROR"
    override val message = string
}
class NotValidName(string: String): Exception(string){
    val result: String = "NAMEERROR"
    override val message = string
}


