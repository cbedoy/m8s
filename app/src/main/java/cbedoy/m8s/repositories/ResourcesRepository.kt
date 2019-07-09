package cbedoy.m8s.repositories

import androidx.lifecycle.MutableLiveData
import cbedoy.m8s.models.Resource
import cbedoy.m8s.models.User
import cbedoy.m8s.services.ResourcesService
import cbedoy.m8s.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ResourcesRepository {
    private var service: ResourcesService = RetrofitService.createService(ResourcesService::class.java)


    fun getResources(user: User) : MutableLiveData<List<Resource>> {
        val mutableLiveData = MutableLiveData<List<Resource>>()

        val call = service.getResources(user.id, true, Integer.MAX_VALUE)
        call.enqueue(object : Callback<List<Resource>>{
            override fun onFailure(call: Call<List<Resource>>, t: Throwable) {
                mutableLiveData.value = ArrayList()
            }

            override fun onResponse(call: Call<List<Resource>>, response: Response<List<Resource>>) {
                mutableLiveData.value = response.body()
            }
        })

        return mutableLiveData
    }
}