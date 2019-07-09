package cbedoy.m8s.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cbedoy.m8s.models.Resource
import cbedoy.m8s.models.User
import cbedoy.m8s.repositories.ResourcesRepository

class ResourcesViewModel : ViewModel(){
    lateinit var user: User

    val resources: LiveData<List<Resource>> by lazy {
        loadResources(user)
    }

    private fun loadResources(user: User) : MutableLiveData<List<Resource>> {
        return ResourcesRepository.getResources(user)
    }
}