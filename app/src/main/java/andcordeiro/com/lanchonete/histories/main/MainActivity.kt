package andcordeiro.com.lanchonete.histories.main

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.histories.menu.MenuFragment
import andcordeiro.com.lanchonete.histories.order.OrderFragment
import andcordeiro.com.lanchonete.histories.promotion.PromotionFragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu -> {
                toolbar.title = getString(R.string.menu_title)
                val menuFragment = MenuFragment.newInstance()
                openFragment(menuFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.promotion -> {
                toolbar.title = getString(R.string.promotion_title)
                val promotionFragment = PromotionFragment.newInstance()
                openFragment(promotionFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.order -> {
                toolbar.title = getString(R.string.order_title)
                val orderFragment = OrderFragment.newInstance()
                openFragment(orderFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bnv_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        toolbar.title = getString(R.string.menu_title)
        openFragment(MenuFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        val args = Bundle()
        args.putString(getString(R.string.name_args), MainActivity::class.java.name)
        fragment.arguments = args
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.commit()
    }
}
