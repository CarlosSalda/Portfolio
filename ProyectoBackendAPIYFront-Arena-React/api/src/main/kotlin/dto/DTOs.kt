package dto

data class UserLoginDTO(val email: String, val password: String)
data class UserRegisterDTO(val name:String, val email: String, val password: String, val image:String)