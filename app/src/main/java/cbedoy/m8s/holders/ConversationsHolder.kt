package cbedoy.m8s.holders

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import cbedoy.m8s.R
import cbedoy.m8s.models.Conversation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.conversation_cell.*
import cbedoy.m8s.utils.UtilsProvider


class ConversationsHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
    fun reload(conversation: Conversation){
        name_view.text = conversation.name
        date_view.visibility = View.INVISIBLE

        val drawableResource = UtilsProvider.drawableResource(name_view.context, R.color.error_color)

        val requestOptions =  RequestOptions
            .circleCropTransform()

        Glide.with(avatar_view)
            .load(conversation.avatar)
            .apply(requestOptions)
            .error(drawableResource)
            .placeholder(drawableResource)
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