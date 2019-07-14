package cbedoy.m8s.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cbedoy.m8s.models.Conversation
import cbedoy.m8s.repositories.ConversationsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ConversationsViewModel : NotificationStateViewModel(){

    private val _conversations = MutableLiveData<List<Conversation>>()
    val conversations: LiveData<List<Conversation>> = _conversations

    private var job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO )

    fun loadConversations() {
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            _conversations.postValue(ConversationsRepository.loadConversations())
            _state.postValue(NotificationState.ERROR)
        }
    }
}