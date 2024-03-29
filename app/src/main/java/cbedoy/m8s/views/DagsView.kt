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
import cbedoy.m8s.viewmodels.ConversationsViewModel
import cbedoy.m8s.viewmodels.NotificationStateViewModel
import cbedoy.m8s.viewmodels.NotificationStateViewModel.NotificationState.*
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
        viewModel.conversations.observe(this, Observer { conversations ->
            conversations.forEach {
                if (!dataSource.contains(it.id)){
                    adapter.dataModel.add(it)
                    dataSource.add(it.id)
                }
            }

            adapter.notifyDataSetChanged()
        })
        viewModel.state.observe(this, Observer { notificationState ->
            when(notificationState){
                LOADING -> {
                    base_view_recycler_view.visibility = View.INVISIBLE
                    base_view_empty_data_container.visibility = View.INVISIBLE
                }
                NONE, DONE -> {
                    base_view_recycler_view.visibility = View.VISIBLE
                    base_view_empty_data_container.visibility = View.INVISIBLE
                }
                ERROR -> {
                    base_view_recycler_view.visibility = View.INVISIBLE
                    base_view_empty_data_container.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadConversations()
    }

    override fun onPause() {
        super.onPause()

        dataSource.clear()
        adapter.dataModel.clear()

        adapter.notifyDataSetChanged()
    }
}