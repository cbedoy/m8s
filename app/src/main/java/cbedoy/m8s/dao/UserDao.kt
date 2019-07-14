package cbedoy.m8s.dao

import androidx.room.*
import cbedoy.m8s.models.User

@Dao
interface UserDao{
    @Query("SELECT * FROM user ORDER BY firstName ASC")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    suspend fun loadAllByIds(userIds: List<String>): List<User>

    @Query("SELECT * FROM user WHERE firstName LIKE :first AND " +
            "lastName LIKE :last LIMIT 1")
    suspend fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
}