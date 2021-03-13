package dto

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User

class ResultSearchByUser(users: List<User>, instagramSystem: InstagramSystem) {

    val content = toUsersWithOutTimeLineDTO(users, instagramSystem)

    private fun toUsersWithOutTimeLineDTO(users: List<User>, instagramSystem: InstagramSystem): List<UserWithOutTimeLineDTO> {
        var result:MutableList<UserWithOutTimeLineDTO> = mutableListOf()

        for(user in users) {
            result.add(UserWithOutTimeLineDTO(user, instagramSystem))
        }

        return result.toList()
    }


}