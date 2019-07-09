package cbedoy.m8s.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cbedoy.m8s.R
import cbedoy.m8s.adapters.ConversationsAdapter
import cbedoy.m8s.models.User
import cbedoy.m8s.viewmodels.ConversationsViewModel
import kotlinx.android.synthetic.main.base_view.*

class DagsView : Fragment() {
    lateinit var viewModel: ConversationsViewModel
    var dataSource =  ArrayList<String>()
    var adapter =  ConversationsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.base_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        base_view_recycler_view.setHasFixedSize(true)
        base_view_recycler_view.layoutManager = LinearLayoutManager(context)
        base_view_recycler_view.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(ConversationsViewModel::class.java)
        viewModel.user = User().apply {
            id = "2845fe481e74b9010a7913d7b214a8937972d6b1"
            college = "50592380-a016-4681-8622-482e5ea44b95"
        }
        viewModel.conversations.observe(this, Observer { conversations ->
            conversations.forEach {
                if (!dataSource.contains(it.id)){
                    adapter.dataModel.add(it)
                    dataSource.add(it.id)

                    adapter.notifyItemInserted(conversations.indexOf(it))
                }else{
                    adapter.notifyItemChanged(conversations.indexOf(it))
                }
            }
        })
    }
}