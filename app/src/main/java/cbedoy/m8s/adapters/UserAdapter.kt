package cbedoy.m8s.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cbedoy.m8s.R
import cbedoy.m8s.holders.BaseHolder
import cbedoy.m8s.holders.SectionHolder
import cbedoy.m8s.holders.UserHolder
import cbedoy.m8s.models.Section
import cbedoy.m8s.models.User

class UserAdapter : RecyclerView.Adapter<BaseHolder>(){
    var dataModel : ArrayList<Any> = ArrayList()

    private val types = arrayOf(User::class, Section::class)

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): BaseHolder {
        return if (Section::class == types[type]){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.user_section_cell, parent, false)
            SectionHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.user_cell, parent, false)
            UserHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return types.indexOf(dataModel[position]::class)
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.reload(dataModel[position])
    }

}