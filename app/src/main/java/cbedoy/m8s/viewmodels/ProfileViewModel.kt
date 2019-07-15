package cbedoy.m8s.viewmodels

import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cbedoy.m8s.models.Common
import cbedoy.m8s.models.Common.*
import cbedoy.m8s.models.User
import cbedoy.m8s.repositories.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel : NotificationStateViewModel() {

    private val _profile = MutableLiveData<List<Any>>()
    val profile: LiveData<List<Any>> = _profile

    fun loadProfile() {
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            val profile = ProfileRepository.loadProfile()
            profile.add(User().apply {
                avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRO0Q3HKtsSHx4ip-NFdzarmGyxTZqeeF1E_INx8ToEHphgunJlkg"
                firstname = "Carlos"
                lastname = "Bedoy"
                email = "carlos@gmail.com"
                type = "Musician Developer"
            })
            profile.add(Margin())
            profile.add(Title("Settings"))
            profile.add(
                Switch("Notifications", true,
                CompoundButton.OnCheckedChangeListener { _, isChecked ->
                    if (isChecked) enableNotifications() else disableNotifications()
                })
            )
            profile.add(Margin())
            profile.add(Title("Other Information"))
            profile.add(TitleDescription("Degree", "Code & Music", View.OnClickListener {

            }))
            profile.add(TitleDescription("Age", "28"))
            profile.add(TitleDescription("Instrument", "Guitar, Bass, Keys, Synth"))
            profile.add(TitleDescription("Title", "Mobile Head"))
            profile.add(TitleDescription("Sport", "GYM"))
            profile.add(Margin())
            profile.add(Credit())
            profile.add(Margin())
            _profile.postValue(profile)
            _state.postValue(NotificationState.DONE)
        }
    }

    private fun enableNotifications() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun disableNotifications() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}