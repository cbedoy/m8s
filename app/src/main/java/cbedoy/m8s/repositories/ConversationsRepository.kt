package cbedoy.m8s.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import cbedoy.m8s.dao.AppDatabase
import cbedoy.m8s.dao.ConversationDao
import cbedoy.m8s.dao.UserDao
import cbedoy.m8s.models.Conversation
import cbedoy.m8s.models.User
import cbedoy.m8s.services.ConversationsService
import cbedoy.m8s.utils.RetrofitService
import kotlinx.coroutines.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

object ConversationsRepository : AnkoLogger{

    @Volatile
    lateinit var user: User
    private lateinit var userDao : UserDao
    private lateinit var conversationDao : ConversationDao

    private var repositoryJob = Job()
    private val coroutineScope = CoroutineScope(repositoryJob + Dispatchers.IO )


    fun init(application: Application){
        val appDatabase = AppDatabase.getInstance(application)
        if (appDatabase != null) {
            userDao = appDatabase.userDao()
            conversationDao = appDatabase.conversationDao()
        }
    }

    private var service: ConversationsService =
        RetrofitService.createService(ConversationsService::class.java)

    fun loadConversations(user: User): MutableLiveData<List<Conversation>> {
        val mutableLiveData = MutableLiveData<List<Conversation>>()
        coroutineScope.launch {
            try {
                mutableLiveData.postValue(conversationDao.getAll())

                val call = service.getConversations(user.id)
                val list = call.await()
                list.forEach {
                    decorateConversation(it, user)
                }
                mutableLiveData.postValue(list)
            }catch (exception: Exception){
                mutableLiveData.postValue(conversationDao.getAll())
            }
        }
        return mutableLiveData
    }

    private fun decorateConversation(conversation: Conversation, targetUser: User) {
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