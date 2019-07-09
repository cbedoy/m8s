package cbedoy.m8s

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cbedoy.m8s.models.User
import cbedoy.m8s.viewmodels.UsersViewModel
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)

        val viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        viewModel.user = User().apply {
            id = "2845fe481e74b9010a7913d7b214a8937972d6b1"
            college = "50592380-a016-4681-8622-482e5ea44b95"
        }
        viewModel.directory.observe(this, Observer {
                viewModel.conversations.observe(this, Observer {
                    startActivity<MainActivity>()
                })
        })
    }
}