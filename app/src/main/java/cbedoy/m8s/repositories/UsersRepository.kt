package cbedoy.m8s.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import cbedoy.m8s.dao.AppDatabase
import cbedoy.m8s.dao.UserDao
import cbedoy.m8s.models.User
import cbedoy.m8s.services.UserService
import cbedoy.m8s.utils.RetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object UsersRepository {
    private var service: UserService = RetrofitService.createService(UserService::class.java)
    private lateinit var userDao : UserDao

    fun init(application: Application){
        val appDatabase = AppDatabase.getInstance(application)
        if (appDatabase != null) {
            userDao = appDatabase.userDao()
        }
    }

    fun getDirectory(user: User) : MutableLiveData<List<User>>{
        val mutableLiveData = MutableLiveData<List<User>>()
        val call = service.getCollege(user.college!!, user.id)
        call.enqueue(object : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                mutableLiveData.value = ArrayList()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                prepareResults(mutableLiveData, response)
            }
        })
        return mutableLiveData
    }

    private fun prepareResults(mutableLiveData: MutableLiveData<List<User>>, response: Response<List<User>>) {
        if (response.isSuccessful){
            val list = response.body()
            GlobalScope.launch {
                if (list != null)
                    userDao.insertAll(list)

                mutableLiveData.postValue(list)
            }
        }else{
            mutableLiveData.value = userDao.getAll()
        }
    }
}