package andcordeiro.com.lanchonete.histories.addorder

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.histories.menu.MenuFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

class AddOrderActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

        toolbar = supportActionBar!!
        toolbar.title = getString(R.string.add_order_title)
        openFragment(MenuFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        val args = Bundle()
        args.putString(getString(R.string.name_args), AddOrderActivity::class.java.name)
        fragment.arguments = args
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.commit()
    }
}
