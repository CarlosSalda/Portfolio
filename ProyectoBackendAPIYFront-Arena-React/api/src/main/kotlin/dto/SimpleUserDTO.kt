package dto


import org.unq.ui.model.User

class SimpleUserDTOList(val users: MutableList<User>) {

    fun myLikes(): MutableList<SimpleUserDTO> {

        val result: MutableList<SimpleUserDTO> = mutableListOf()

        for (user in users) {
            result.add(SimpleUserDTO(user))
        }
        return result
    }
}

class SimpleUserDTO(user: User) {

    val id = user.id
    val name = user.name
    val image = user.image
}
