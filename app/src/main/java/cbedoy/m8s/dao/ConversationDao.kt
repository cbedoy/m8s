package cbedoy.m8s.dao

import androidx.room.*
import cbedoy.m8s.models.Conversation

@Dao
interface ConversationDao{
    @Query("SELECT * FROM conversation")
    suspend fun getAll(): List<Conversation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(conversations: List<Conversation>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: Conversation)
}