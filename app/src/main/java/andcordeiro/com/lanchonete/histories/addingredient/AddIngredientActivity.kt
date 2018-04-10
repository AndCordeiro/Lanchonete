package andcordeiro.com.lanchonete.histories.addingredient

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.system.extensions.find
import andcordeiro.com.lanchonete.system.extensions.gone
import andcordeiro.com.lanchonete.system.extensions.show
import andcordeiro.com.lanchonete.system.mvp.PresenterHolder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar

class AddIngredientActivity : AppCompatActivity(), AddIngredientContract.View, AddIngredientAdapter.OnClickListener {

    lateinit var toolbar: ActionBar
    private val recyclerView by lazy { find<RecyclerView>(R.id.rv_ingredient) }
    private val pb by lazy { find<ProgressBar>(R.id.pb_ingredient) }
    private var presenter: AddIngredientPresenter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: AddIngredientAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)

        toolbar = supportActionBar!!
        toolbar.title = getString(R.string.add_ingredient_title)

        presenter = createPresenter()
        presenter?.create()
        initViews()
    }

    private fun initViews() {
        layoutManager = LinearLayoutManager(context())
        recyclerView.layoutManager = layoutManager
        loadIngredient()
        pb.show()
    }

    private fun loadIngredient() {
        presenter?.loadIngredient()
    }

    override fun ingredient(ingredients: List<Ingredient>) {
        adapter = AddIngredientAdapter(ingredients as MutableList<Ingredient>, this)
        recyclerView.adapter = adapter
        pb.gone()
    }

    override fun intent(): Intent = intent

    override fun context(): Context = this

    override fun onItemClick(data: Ingredient) {}

    override fun onLongItemClick(data: Ingredient) {}

    private fun createPresenter(): AddIngredientPresenter {
        presenter = PresenterHolder.instance!!.getPresenter(AddIngredientPresenter::class.java)
        if (presenter == null) {
            presenter = AddIngredientPresenter(this)
            PresenterHolder.instance?.putPresenter(AddIngredientPresenter::class.java, presenter!!)
        } else {
            presenter?.view(this)
        }
        return presenter as AddIngredientPresenter
    }
}
