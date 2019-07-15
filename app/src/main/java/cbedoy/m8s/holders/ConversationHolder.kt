package cbedoy.m8s.holders

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import cbedoy.m8s.R
import cbedoy.m8s.models.Conversation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.conversation_cell.*


class ConversationHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
    fun reload(conversation: Conversation){
        name_view.text = conversation.name
        date_view.visibility = View.INVISIBLE

        val requestOptions =  RequestOptions
            .circleCropTransform()

        Glide.with(avatar_view)
            .load(conversation.avatar)
            .apply(requestOptions)
            .error(R.drawable.error_circle_drawable)
            .placeholder(R.drawable.placeholder_circle_drawable)
            .into(avatar_view)

        when(conversation.privacy){
            "public" -> {


                abbreviation_view.visibility = View.VISIBLE
                abbreviation_view.text = conversation.name
            }
            else -> {
                abbreviation_view.visibility = View.INVISIBLE
            }
        }
    }
}