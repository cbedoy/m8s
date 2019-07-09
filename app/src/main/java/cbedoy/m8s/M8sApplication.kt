package cbedoy.m8s

import android.app.Application
import cbedoy.m8s.repositories.ConversationsRepository
import cbedoy.m8s.repositories.UsersRepository

class M8sApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        UsersRepository.init(this)
        ConversationsRepository.init(this)
    }
}