package dto

import org.unq.ui.model.User

class TimeLineUserDTO(user: User) {

    val id= user.id
    val name = user.name
    val image = user.image
}