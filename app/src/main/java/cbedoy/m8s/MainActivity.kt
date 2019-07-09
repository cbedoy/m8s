package cbedoy.m8s

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cbedoy.m8s.views.DagsView
import cbedoy.m8s.views.ExplorerView
import cbedoy.m8s.views.UsersView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val views = hashMapOf(
        "explorer" to ExplorerView(),
        "dags" to DagsView(),
        "users" to UsersView()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.action_explorer -> replaceView(views["explorer"])
                R.id.action_dags -> replaceView(views["dags"])
                R.id.action_users -> replaceView(views["users"])
            }
        }

    }

    private fun replaceView(fragment: Fragment?) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_frame_layout, fragment)
            transaction.addToBackStack(null)

            transaction.commit()
        }
    }
}
