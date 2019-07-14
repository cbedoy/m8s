package cbedoy.m8s.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cbedoy.m8s.models.Conversation
import cbedoy.m8s.models.User
import cbedoy.m8s.repositories.ConversationsRepository
import cbedoy.m8s.repositories.UsersRepository
import kotlinx.coroutines.*

class UsersViewModel: NotificationStateViewModel(){

    private var job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO )

    private val _directory = MutableLiveData<List<User>>()
    val directory: LiveData<List<User>> = _directory

    private val _userDirectory = MutableLiveData<List<Any>>()
    val userDirectory: LiveData<List<Any>> = _userDirectory

    private val _conversations = MutableLiveData<List<Conversation>>()
    val conversations: LiveData<List<Conversation>> = _conversations

    fun loadDirectory() {
        scope.launch {
            _directory.postValue(UsersRepository.getDirectory())
        }
    }

    fun loadUserDirectory() {
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            _userDirectory.postValue(UsersRepository.prepareUserDirectory())
            _state.postValue(NotificationState.DONE)
        }
    }

    fun loadConversations(){
        scope.launch {
            _conversations.postValue(ConversationsRepository.loadConversations())
        }
    }


    override fun onCleared() {
        super.onCleared()

        scope.cancel()
    }

}