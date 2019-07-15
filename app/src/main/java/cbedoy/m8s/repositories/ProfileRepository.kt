package cbedoy.m8s.repositories

import android.app.Application
import cbedoy.m8s.dao.AppDatabase
import cbedoy.m8s.dao.ConversationDao
import cbedoy.m8s.dao.UserDao
import cbedoy.m8s.models.Common
import cbedoy.m8s.utils.UtilsProvider

object ProfileRepository {
    private lateinit var userDao : UserDao
    private lateinit var conversationDao : ConversationDao

    fun init(application: Application){
        val appDatabase = AppDatabase.getInstance(application)
        if (appDatabase != null) {
            userDao = appDatabase.userDao()
            conversationDao = appDatabase.conversationDao()
        }
    }

    suspend fun loadProfile() : ArrayList<Any>{
        val result = ArrayList<Any>()
        val query = userDao.loadAllByIds(arrayListOf(UtilsProvider.sessionUser().id))

        if (query.isNotEmpty())
            result.add(query.first())

        return result
    }
}