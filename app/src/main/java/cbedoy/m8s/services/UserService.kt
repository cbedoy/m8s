package cbedoy.m8s.services

import cbedoy.m8s.models.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("user/{id}")
    fun getUser(@Path("id") id: String) : Call<User>

    @GET("college/{college}/directory")
    fun getCollege(@Path("college") id: String, @Query("user_id") user: String) : Call<List<User>>

    @GET("user/{id}")
    fun getUserAsObservable(@Path("id") id: String) : Observable<User>

    @GET("user/{id}")
    fun getCollegeAsObservable(@Path("id") id: String, @Query("user_id") user: String) : Observable<List<User>>


}