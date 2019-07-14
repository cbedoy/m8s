package cbedoy.m8s

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cbedoy.m8s.viewmodels.UsersViewModel
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)

        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        viewModel.directory.observe(this, Observer {
            viewModel.loadConversations()
        })

        viewModel.conversations.observe(this, Observer {
            startActivity<MainActivity>()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadDirectory()
    }
}