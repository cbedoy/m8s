package cbedoy.m8s.dao

import androidx.room.*
import cbedoy.m8s.models.Conversation

@Dao
interface ConversationDao{
    @Query("SELECT * FROM conversation")
    fun getAll(): List<Conversation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(conversations: List<Conversation>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(conversation: Conversation)
}