package windows.mainPage

import org.unq.ui.bootstrap.getInstagramSystem
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import models.mainPage.WelcomeToInstagramModel

class InstagramApp : Application() {
    override fun createMainWindow() : Window<*> {
        val model = WelcomeToInstagramModel(getInstagramSystem())
        return WelcomeToInstagramWindow(this, model)
    }
}