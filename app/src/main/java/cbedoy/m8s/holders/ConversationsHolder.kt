package cbedoy.m8s.holders

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import cbedoy.m8s.R
import cbedoy.m8s.models.Conversation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.conversation_cell.*

class ConversationsHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
    fun reload(conversation: Conversation){
        name_view.text = conversation.name
        date_view.visibility = View.INVISIBLE

        Glide.with(avatar_view)
            .load(conversation.avatar)
            .apply(RequestOptions.placeholderOf(R.color.colorPrimaryDark).circleCrop())
            .apply(RequestOptions.errorOf(R.color.colorPrimaryDark).circleCrop())
            .apply(RequestOptions.circleCropTransform())
            .into(avatar_view)
    }
}