package andcordeiro.com.lanchonete.histories.menu

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Sandwich
import andcordeiro.com.lanchonete.histories.addingredient.AddIngredientActivity
import andcordeiro.com.lanchonete.system.extensions.find
import andcordeiro.com.lanchonete.system.extensions.gone
import andcordeiro.com.lanchonete.system.extensions.show
import andcordeiro.com.lanchonete.system.mvp.PresenterHolder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

class MenuFragment : Fragment(), MenuContract.View, MenuAdapter.OnClickListener {

    private val recyclerView by lazy { find<RecyclerView>(R.id.rv_menu)  }
    private val pb by lazy { find<ProgressBar>(R.id.pb_menu) }
    private var presenter: MenuPresenter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: MenuAdapter? = null

    companion object {
        fun newInstance(): MenuFragment = MenuFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_menu, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter()
        presenter?.create()
        initViews()
    }

    private fun initViews() {
        layoutManager = LinearLayoutManager(context())
        recyclerView.layoutManager = layoutManager
        loadMenu()
        pb.show()
    }

    private fun loadMenu(){
        presenter?.loadMenu()
    }

    override fun sandwiches(sandwiches: List<Sandwich>) {
        adapter = MenuAdapter(arguments?.getString("NAME"),this, sandwiches as MutableList<Sandwich>, this)
        recyclerView.adapter = adapter
        pb.gone()
    }

    override fun intent(): Intent = activity!!.intent

    override fun context(): Context = activity!!.baseContext

    override fun onItemClick(data: Sandwich) {}

    override fun onLongItemClick(data: Sandwich) {}

    override fun onButtonClick(data: Sandwich) {
        startActivity(Intent(context(), AddIngredientActivity::class.java))
    }

    override fun priceSandwich(ingredients: List<Ingredient>?): Double? = presenter?.priceSandwich(ingredients)

    private fun createPresenter(): MenuPresenter {
        presenter = PresenterHolder.instance!!.getPresenter(MenuPresenter::class.java)
        if (presenter == null) {
            presenter = MenuPresenter(this)
            PresenterHolder.instance?.putPresenter(MenuPresenter::class.java, presenter!!)
        } else {
            presenter?.view(this)
        }
        return presenter as MenuPresenter
    }


}