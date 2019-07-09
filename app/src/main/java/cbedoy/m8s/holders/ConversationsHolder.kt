package cbedoy.m8s.holders

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import cbedoy.m8s.models.Conversation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.conversation_cell.*

class ConversationsHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
    fun reload(conversation: Conversation){
        name_view.text = conversation.description
    }
}