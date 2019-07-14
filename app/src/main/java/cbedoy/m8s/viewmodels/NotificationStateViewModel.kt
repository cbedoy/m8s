package cbedoy.m8s.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class NotificationStateViewModel : ViewModel(){

    val _state = MutableLiveData<NotificationState>()
    val state: LiveData<NotificationState> = _state

    enum class NotificationState {
        LOADING, NONE, ERROR, DONE
    }

    init {
        _state.postValue(NotificationState.NONE)
    }
}