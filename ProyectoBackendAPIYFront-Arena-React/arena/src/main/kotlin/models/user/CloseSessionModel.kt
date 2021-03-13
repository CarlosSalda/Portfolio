package models.user

import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable


@Observable
class CloseSessionModel(val user: User){
    val name = user.name
}