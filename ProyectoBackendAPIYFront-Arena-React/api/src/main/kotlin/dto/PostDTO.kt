package dto

import org.unq.ui.model.Comment
import org.unq.ui.model.Post
import org.unq.ui.model.User

class PostDTO(post: Post) {

    val id = post.id
    val description = post.description
    val portrait = post.portrait
    val landscape = post.landscape
    val likes = SimpleUserDTOList(post.likes).myLikes()
    val fecha = post.date
    val user = SimpleUserDTO(post.user)
    val comments = myComments(post.comments)

    private fun myComments(comments: MutableList<Comment>): MutableList<CommentDTO> {

        val result: MutableList<CommentDTO> = mutableListOf()
        for (comment in comments) {
            result.add(CommentDTO(comment))
        }
        return result
    }
}
