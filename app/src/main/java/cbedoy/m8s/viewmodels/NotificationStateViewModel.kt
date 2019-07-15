package cbedoy.m8s.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class NotificationStateViewModel : ViewModel(){

    private var job = Job()

    val _state = MutableLiveData<NotificationState>()
    val state: LiveData<NotificationState> = _state
    val scope = CoroutineScope(job + Dispatchers.IO )

    enum class NotificationState {
        LOADING, NONE, ERROR, DONE
    }

    init {
        _state.postValue(NotificationState.NONE)
    }

    override fun onCleared() {
        super.onCleared()

        scope.cancel()
    }



}