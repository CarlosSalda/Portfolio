package models.user

import windows.user.LoginWindow
import org.uqbar.arena.windows.*
import org.uqbar.arena.Application
import org.unq.ui.model.InstagramSystem

class LoginViewApplication : Application() {
    private var system = InstagramSystem()
    override fun createMainWindow(): Window<*> {
        return LoginWindow(this, LoginModel(system))
    }
}

fun main() {
    LoginViewApplication().start()
}