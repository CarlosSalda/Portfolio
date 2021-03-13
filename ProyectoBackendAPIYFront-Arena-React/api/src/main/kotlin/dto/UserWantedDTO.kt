package dto

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User

class UserWantedDTO(user: User, instagramSystem: InstagramSystem) {
    val id= user.id
    val name = user.name
    val image = user.image
    val followers = myFollowers(user, instagramSystem)
    val posts = myPosts(user, instagramSystem)

    private fun myPosts(user: User, instagramSystem: InstagramSystem): MutableList<PostDTO> {
        val myPosts = instagramSystem.searchByUserId(user.id)
        val result: MutableList<PostDTO> = mutableListOf()

        for (post in myPosts) {
            result.add(PostDTO(post))
        }

        return  result
    }


    private fun myFollowers(user: User, instagramSystem: InstagramSystem): MutableList<SimpleUserDTO> {

        val myFollowers = instagramSystem.getUser(user.id).followers
        val result: MutableList<SimpleUserDTO> = mutableListOf()

        for (follower in myFollowers) {
            result.add(SimpleUserDTO(follower))
        }

        return result
    }

}