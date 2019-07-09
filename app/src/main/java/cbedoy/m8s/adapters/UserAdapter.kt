package cbedoy.m8s.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cbedoy.m8s.R
import cbedoy.m8s.holders.UserHolder
import cbedoy.m8s.models.User

class UserAdapter : RecyclerView.Adapter<UserHolder>(){
    var dataModel : ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_cell, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.reload(dataModel[position])
    }

}