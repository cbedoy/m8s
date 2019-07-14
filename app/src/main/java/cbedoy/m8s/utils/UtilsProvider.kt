package cbedoy.m8s.utils

import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import cbedoy.m8s.models.User

object UtilsProvider {


    private var user: User = User().apply {
        id = "2845fe481e74b9010a7913d7b214a8937972d6b1"
        college = "50592380-a016-4681-8622-482e5ea44b95"
    }

    fun sessionUser() : User{
        return user
    }

    fun drawableResource(context: Context, resourceId: Int): RoundedBitmapDrawable {
        val placeholder = BitmapFactory.decodeResource(context.resources, resourceId)
        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, placeholder)
        circularBitmapDrawable.isCircular = true

        return circularBitmapDrawable
    }
}