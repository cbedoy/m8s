package cbedoy.m8s

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import cbedoy.m8s.repositories.ConversationsRepository
import cbedoy.m8s.repositories.UsersRepository
import androidx.lifecycle.OnLifecycleEvent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug


class M8sApplication: Application(), AnkoLogger {
    override fun onCreate() {
        super.onCreate()

        UsersRepository.init(this)
        ConversationsRepository.init(this)

        ProcessLifecycleOwner.get().lifecycle.addObserver(object  : LifecycleObserver{
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onEnterForeground() {
                //run the code we need
                debug("onEnterForeground")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onEnterBackground() {
                //run the code we need
                debug("onEnterBackground")
            }
        })
    }
}