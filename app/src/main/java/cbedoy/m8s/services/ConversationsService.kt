package cbedoy.m8s.services

import cbedoy.m8s.models.Conversation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ConversationsService {
    @GET("user/{id}/conversations")
    fun getConversations(@Path("id") id: String) : Call<List<Conversation>>
}