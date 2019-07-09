package cbedoy.m8s.holders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.m8s.R
import cbedoy.m8s.models.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_cell.*

class UserHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
    @SuppressLint("SetTextI18n")
    fun reload(user: User) {
        name_view.text = "${user.firstname} ${user.lastname}"

        Glide.with(avatar_view)
            .load(user.avatar)
            .apply(RequestOptions.placeholderOf(R.color.colorAccent).circleCrop())
            .apply(RequestOptions.errorOf(R.color.colorAccent).circleCrop())
            .apply(RequestOptions.circleCropTransform())
            .into(avatar_view)
    }
}