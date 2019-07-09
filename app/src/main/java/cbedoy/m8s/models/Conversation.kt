package cbedoy.m8s.models

import androidx.room.*
import cbedoy.m8s.utils.StringConverter

@Entity
open class Conversation {
    @PrimaryKey
    lateinit var id: String

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "type")
    var type: String? = null

    @ColumnInfo(name = "creationDate")
    var creation_date: Double = 0.0

    @ColumnInfo(name = "privacy")
    var privacy: String? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "avatar")
    var avatar: String? = null

    @TypeConverters(StringConverter::class)
    @ColumnInfo(name = "members")
    var members: List<String>? = null

    @Ignore
    var users: List<User>? = null
}