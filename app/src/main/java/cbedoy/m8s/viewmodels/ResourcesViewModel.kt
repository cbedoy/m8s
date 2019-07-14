package cbedoy.m8s.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cbedoy.m8s.models.Resource
import cbedoy.m8s.repositories.ResourcesRepository

class ResourcesViewModel : ViewModel(){
    val resources: LiveData<List<Resource>> by lazy {
        loadResources()
    }

    private fun loadResources() : MutableLiveData<List<Resource>> {
        return ResourcesRepository.getResources()
    }
}