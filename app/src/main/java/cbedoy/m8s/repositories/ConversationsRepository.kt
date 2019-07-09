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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ConversationsRepository : AnkoLogger{

    lateinit var user: User
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

    fun loadConversations(user: User): MutableLiveData<List<Conversation>> {
        val mutableLiveData = MutableLiveData<List<Conversation>>()
        val call = service.getConversations(user.id)
        call.enqueue(object : Callback<List<Conversation>> {
            override fun onFailure(call: Call<List<Conversation>>, t: Throwable) {
                mutableLiveData.value = ArrayList()
            }

            override fun onResponse(call: Call<List<Conversation>>, response: Response<List<Conversation>>) {
                prepareResults(mutableLiveData, response)
            }
        })
        return mutableLiveData
    }

    private fun prepareResults(
        mutableLiveData: MutableLiveData<List<Conversation>>,
        response: Response<List<Conversation>>
    ) {
        if (response.isSuccessful){
            val list = response.body()
            GlobalScope.launch(Dispatchers.IO) {
                list?.forEach {
                    decorateConversation(it)
                }

                mutableLiveData.postValue(list)
            }
        }else{
            mutableLiveData.value = conversationDao.getAll()
        }
    }

    private fun decorateConversation(conversation: Conversation) {
        var members : List<User> = ArrayList()
        if (conversation.members != null) {
            members = userDao.loadAllByIds(conversation.members!!)
            //it.users = members
            info(members)
        }
        if (conversation.type == "p2p"){
            members.forEach {
                if (it.id != user.id){
                    conversation.name = it.firstname
                    conversation.avatar = it.avatar
                }
            }
        }
        conversationDao.insert(conversation)
    }

}