package cbedoy.m8s.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cbedoy.m8s.models.Conversation
import cbedoy.m8s.models.User
import cbedoy.m8s.repositories.ConversationsRepository
import cbedoy.m8s.repositories.UsersRepository

class UsersViewModel : ViewModel(){

    lateinit var user: User

    val directory: LiveData<List<User>> by lazy {
        loadDirectory(user)
    }

    val conversations: LiveData<List<Conversation>> by lazy {
        loadConversations(user)
    }

    private fun loadDirectory(user: User) : MutableLiveData<List<User>> {
        return UsersRepository.getDirectory(user)
    }

    private fun loadConversations(user: User) : MutableLiveData<List<Conversation>> {
        return ConversationsRepository.loadConversations(user)
    }
}