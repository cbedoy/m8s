package cbedoy.m8s

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cbedoy.m8s.adapters.ConversationsAdapter
import cbedoy.m8s.models.Conversation
import kotlinx.android.synthetic.main.conversations_controller.*
import androidx.recyclerview.widget.DividerItemDecoration
import cbedoy.m8s.viewmodels.ConversationsViewModel
import cbedoy.m8s.viewmodels.UsersViewModel
import org.jetbrains.anko.support.v4.toast


class ConversationsFragment : Fragment() {

    private val adapter: ConversationsAdapter = ConversationsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.conversations_controller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        conversations_recycler_view.adapter = adapter
        conversations_recycler_view.layoutManager = LinearLayoutManager(context)
        conversations_recycler_view.setHasFixedSize(true)

        val decoration = DividerItemDecoration(context, LinearLayout.VERTICAL)
        conversations_recycler_view.addItemDecoration(decoration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val conversationsViewModel: ConversationsViewModel = ViewModelProviders.of(this).get(ConversationsViewModel::class.java)

        conversationsViewModel.conversations.observe(viewLifecycleOwner, Observer { conversations ->
            conversations.forEach {
                adapter.dataModel.add(it)

                adapter.notifyDataSetChanged()
            }
        })
    }
}