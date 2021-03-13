package dto

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User

class UserLoggedDTOUserPath(user: User, instagramSystem: InstagramSystem) {

    val idUser= user.id
    val name = user.name
    val image = user.image
    val followers = myFollowers(user, instagramSystem)
    val timeline = myTimeline(user, instagramSystem)

    private fun myTimeline(user: User, instagramSystem: InstagramSystem): MutableList<TimeLineDTO> {
        val myTimeline = instagramSystem.timeline(user.id)
        val result: MutableList<TimeLineDTO> = mutableListOf()

        for (timeline in myTimeline) {
            result.add(TimeLineDTO(timeline))
        }

        return result
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