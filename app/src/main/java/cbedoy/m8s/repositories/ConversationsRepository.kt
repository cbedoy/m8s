package cbedoy.m8s.repositories

import android.app.Application
import cbedoy.m8s.dao.AppDatabase
import cbedoy.m8s.dao.ConversationDao
import cbedoy.m8s.dao.UserDao
import cbedoy.m8s.models.Conversation
import cbedoy.m8s.models.User
import cbedoy.m8s.services.ConversationsService
import cbedoy.m8s.utils.RetrofitService
import cbedoy.m8s.utils.UtilsProvider
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

object ConversationsRepository : AnkoLogger{
    private lateinit var userDao : UserDao
    private lateinit var conversationDao : ConversationDao

    fun init(application: Application){
        val appDatabase = AppDatabase.getInstance(application)
        if (appDatabase != null) {
            userDao = appDatabase.userDao()
            conversationDao = appDatabase.conversationDao()
        }
    }

    private var service: ConversationsService =
        RetrofitService.createService(ConversationsService::class.java)

    suspend fun loadConversations(): List<Conversation> {
        val user = UtilsProvider.sessionUser()
        return try {
            val call = service.getConversations(user.id)
            val list = call.await()
            list.forEach {
                decorateConversation(it, user)
            }
            list
        }catch (exception: Exception){
            conversationDao.getAll()
        }
    }

    private suspend fun decorateConversation(conversation: Conversation, targetUser: User) {
        var members : List<User> = ArrayList()
        if (conversation.members != null) {
            members = userDao.loadAllByIds(conversation.members!!)
            //it.users = members
            info(members)
        }
        if (conversation.type == "p2p"){
            members.forEach {
                if (it.id != targetUser.id){
                    conversation.name = it.firstname
                    conversation.avatar = it.avatar
                }
            }
        }else{
            conversation.name = conversation.description
        }
        conversationDao.insert(conversation)
    }

}