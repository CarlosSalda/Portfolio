package dto

import org.unq.ui.model.Comment

class CommentDTO(comment: Comment) {

    val id = comment.id
    val body = comment.body
    val user = SimpleUserDTO(comment.user)
}
