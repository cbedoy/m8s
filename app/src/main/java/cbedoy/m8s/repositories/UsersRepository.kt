package cbedoy.m8s.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import cbedoy.m8s.dao.AppDatabase
import cbedoy.m8s.dao.UserDao
import cbedoy.m8s.models.Section
import cbedoy.m8s.models.User
import cbedoy.m8s.services.UserService
import cbedoy.m8s.utils.RetrofitService
import cbedoy.m8s.utils.UtilsProvider
import org.jetbrains.anko.AnkoLogger


object UsersRepository : AnkoLogger {
    private var service: UserService = RetrofitService.createService(UserService::class.java)
    private lateinit var userDao : UserDao

    fun init(application: Application){
        val appDatabase = AppDatabase.getInstance(application)
        if (appDatabase != null) {
            userDao = appDatabase.userDao()
        }
    }

    suspend fun getDirectory() : List<User>{
        val user = UtilsProvider.sessionUser()
        val call = service.getCollege(user.college!!, user.id)
        try {
            val userList = call.await()

            userDao.insertAll(userList)
        }catch (e: Exception){
            error(e)
        }
        return userDao.getAll()
    }

    suspend fun prepareUserDirectory() : List<Any> {
        val result =  ArrayList<Any>()
        val userList = userDao.getAll()

        var character = firstLetter(userList[0])
        result.add(Section().apply {
            title = character
        })

        userList.forEach {
            val currentCharacter = firstLetter(it)
            if (currentCharacter != character){
                result.add(Section().apply {
                    title = currentCharacter
                })

                character = currentCharacter
            }
            result.add(it)
        }
        return result
    }

    private fun firstLetter(user: User) : String{
        if (user.firstname == null){
            return ""
        }
        return user.firstname?.get(0).toString().toUpperCase()
    }

}