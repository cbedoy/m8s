package cbedoy.m8s.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cbedoy.m8s.R
import cbedoy.m8s.holders.*
import cbedoy.m8s.models.Common.*
import cbedoy.m8s.models.User

class ProfileAdapter : RecyclerView.Adapter<BaseHolder>(){
    var dataModel : ArrayList<Any> = ArrayList()

    private val types = arrayOf(Value::class, Switch::class, Margin::class,
        Title::class, Credit::class, TitleDescription::class, User::class)

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): BaseHolder {
        when(types[type]){
            Value::class -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.common_value_cell, parent, false)
                return ValueHolder(view)
            }
            TitleDescription::class -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.common_title_description_cell, parent, false)
                return TitleDescriptionHolder(view)
            }
            Title::class -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.common_title_cell, parent, false)
                return TitleHolder(view)
            }
            Switch::class -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.common_switch_cell, parent, false)
                return SwitchHolder(view)
            }
            User::class -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_user_cell, parent, false)
                return ProfileUserHolder(view)
            }
            Margin::class ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.common_margin_cell, parent, false)
                return MarginHolder(view)
            }
            Credit::class ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.common_credit_cell, parent, false)
                return CreditHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.common_margin_cell, parent, false)
                return MarginHolder(view)
            }
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