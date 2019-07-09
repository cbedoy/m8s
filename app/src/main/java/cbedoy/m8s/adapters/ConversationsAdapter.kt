package cbedoy.m8s.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cbedoy.m8s.holders.ConversationsHolder
import cbedoy.m8s.R
import cbedoy.m8s.models.Conversation

class ConversationsAdapter : RecyclerView.Adapter<ConversationsHolder>(){
    var dataModel : ArrayList<Conversation> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ConversationsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conversation_cell, parent, false)
        return ConversationsHolder(view)
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun onBindViewHolder(holder: ConversationsHolder, position: Int) {
        holder.reload(dataModel[position])
    }

}