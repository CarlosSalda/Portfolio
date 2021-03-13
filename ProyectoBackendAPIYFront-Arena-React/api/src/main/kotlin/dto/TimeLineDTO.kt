package dto

import org.unq.ui.model.Post
import org.unq.ui.model.User

class TimeLineDTO(timeline: Post) {
    val id = timeline.id
    val description = timeline.description
    val portrait = timeline.portrait
    val landscape = timeline.landscape
    val likes = SimpleUserDTOList(timeline.likes).myLikes()
    val date = timeline.date
    val user = TimeLineUserDTO(timeline.user)
}