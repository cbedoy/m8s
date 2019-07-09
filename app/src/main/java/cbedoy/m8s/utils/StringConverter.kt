package cbedoy.m8s.utils

import android.text.TextUtils
import androidx.room.TypeConverter


class StringConverter {
    @TypeConverter
    fun fromArray(list: List<String>) : String{
        return TextUtils.join(",", list)
    }

    @TypeConverter
    fun toArray(values: String) : List<String>{
        return values.split(",")
    }
}