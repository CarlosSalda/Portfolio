package dto

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User

class UserWithOutTimeLineDTO(user: User, instagramSystem: InstagramSystem) {

    val id = user.id
    val name = user.name
    val image = user.image
    val followers = myFollowers(user, instagramSystem)



    private fun myFollowers(user: User, instagramSystem: InstagramSystem): MutableList<SimpleUserDTO> {

        val myFollowers = instagramSystem.getUser(user.id).followers
        val result: MutableList<SimpleUserDTO> = mutableListOf()

        for (follower in myFollowers) {
            result.add(SimpleUserDTO(follower))
        }

        return result
    }

}
