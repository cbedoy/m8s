package cbedoy.m8s.holders

import android.annotation.SuppressLint
import android.view.View
import cbedoy.m8s.R
import cbedoy.m8s.models.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.user_cell.*

class UserHolder (override val containerView: View) : BaseHolder(containerView) {
    @SuppressLint("SetTextI18n")
    override fun reload(any: Any) {
        any as User
        name_view.text = "${any.firstname} ${any.lastname}"

        Glide.with(avatar_view)
            .load(any.avatar)
            .apply(RequestOptions.placeholderOf(R.color.warning_color).circleCrop())
            .apply(RequestOptions.errorOf(R.color.error_color).circleCrop())
            .apply(RequestOptions.circleCropTransform())
            .into(avatar_view)
    }

}