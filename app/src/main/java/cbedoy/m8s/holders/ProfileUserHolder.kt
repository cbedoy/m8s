package cbedoy.m8s.holders

import android.annotation.SuppressLint
import android.view.View
import cbedoy.m8s.R
import cbedoy.m8s.models.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.profile_user_cell.*

class ProfileUserHolder (override val containerView: View) : BaseHolder(containerView) {
    @SuppressLint("SetTextI18n")
    override fun reload(any: Any) {
        any as User

        Glide.with(profile_user_cell_avatar)
            .load(any.avatar)
            .apply(RequestOptions.placeholderOf(R.color.warning_color).circleCrop())
            .apply(RequestOptions.errorOf(R.color.error_color).circleCrop())
            .apply(RequestOptions.circleCropTransform())
            .into(profile_user_cell_avatar)

        profile_user_cell_nickname.text = "${any.firstname} ${any.lastname}"
        profile_user_cell_email.text = any.email
    }
}