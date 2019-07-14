package cbedoy.m8s.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class User : CommonModel{
    @PrimaryKey
    lateinit var id: String

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "type")
    var type: String? = null

    @ColumnInfo(name = "college")
    var college: String? = null

    @ColumnInfo(name = "firstName")
    var firstname: String? = null

    @ColumnInfo(name = "lastName")
    var lastname: String? = null

    @ColumnInfo(name = "avatar")
    var avatar: String? = null

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

    override fun getHolderType(): CommonModel.type {
        return CommonModel.type.user
    }
}

