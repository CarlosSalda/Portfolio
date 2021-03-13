package dto

import org.unq.ui.model.User

class UserDTO(user : User){
    val name = user.name
    val image = user.image
}