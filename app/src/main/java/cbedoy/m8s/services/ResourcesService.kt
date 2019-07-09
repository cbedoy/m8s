package cbedoy.m8s.services


import cbedoy.m8s.models.Resource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ResourcesService {
    @GET("resources")
    fun getResources(@Query("userId") id: String,
                     @Query("schoolWide") schoolWide: Boolean,
                     @Query("limit") limit: Int) : Call<List<Resource>>
}