package cbedoy.m8s.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cbedoy.m8s.holders.ConversationHolder
import cbedoy.m8s.R
import cbedoy.m8s.models.Conversation

class ConversationsAdapter : RecyclerView.Adapter<ConversationHolder>(){
    var dataModel : ArrayList<Conversation> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ConversationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conversation_cell, parent, false)
        return ConversationHolder(view)
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun onBindViewHolder(holder: ConversationHolder, position: Int) {
        holder.reload(dataModel[position])
    }

}