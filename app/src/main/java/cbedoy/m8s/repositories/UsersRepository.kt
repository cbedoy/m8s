package cbedoy.m8s.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import cbedoy.m8s.dao.AppDatabase
import cbedoy.m8s.dao.UserDao
import cbedoy.m8s.models.User
import cbedoy.m8s.services.UserService
import cbedoy.m8s.utils.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


object UsersRepository {
    private var service: UserService = RetrofitService.createService(UserService::class.java)
    private lateinit var userDao : UserDao

    private var repositoryJob = Job()
    private val coroutineScope = CoroutineScope(repositoryJob + Dispatchers.IO )

    fun init(application: Application){
        val appDatabase = AppDatabase.getInstance(application)
        if (appDatabase != null) {
            userDao = appDatabase.userDao()
        }
    }

    fun getDirectory(user: User) : MutableLiveData<List<User>>{
        val mutableLiveData = MutableLiveData<List<User>>()
        coroutineScope.launch {
            val call = service.getCollege(user.college!!, user.id)
            try {
                val userList = call.await()

                userDao.insertAll(userList)

                mutableLiveData.postValue(userList)
            }catch (exception : Exception){
                mutableLiveData.postValue(ArrayList())
            }

        }
        return mutableLiveData
    }


}