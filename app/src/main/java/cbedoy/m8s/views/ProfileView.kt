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
import cbedoy.m8s.adapters.ProfileAdapter
import cbedoy.m8s.viewmodels.NotificationStateViewModel.NotificationState.*
import cbedoy.m8s.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.base_view.*

class ProfileView : Fragment(){

    private lateinit var viewModel: ProfileViewModel
    private var adapter = ProfileAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.base_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        base_view_recycler_view.setHasFixedSize(true)
        base_view_recycler_view.layoutManager = LinearLayoutManager(context)
        base_view_recycler_view.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.profile.observe(this, Observer {
            if(it.isNotEmpty() && adapter.dataModel.isEmpty()){
                adapter.dataModel.addAll(it)

                adapter.notifyDataSetChanged()
            }
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

        viewModel.loadProfile()
    }

    override fun onPause() {
        super.onPause()
        adapter.dataModel.clear()

        adapter.notifyDataSetChanged()
    }
}