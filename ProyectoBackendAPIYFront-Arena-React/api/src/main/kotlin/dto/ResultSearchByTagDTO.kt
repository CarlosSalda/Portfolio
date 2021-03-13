package dto

import dto.PostDTO
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post

class ResultSearchByTagDTO(posts: List<Post>) {

    val content = toPostDTO(posts)

    private fun toPostDTO(posts: List<Post>): List<PostDTO> {
        var result: MutableList<PostDTO> = mutableListOf()

        for (post in posts) {
            result.add(PostDTO(post))
        }
        return  result.toList()
    }
}
