package andcordeiro.com.lanchonete.histories.addingredient

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Sandwich
import andcordeiro.com.lanchonete.system.extensions.find
import andcordeiro.com.lanchonete.system.extensions.gone
import andcordeiro.com.lanchonete.system.extensions.show
import andcordeiro.com.lanchonete.system.mvp.PresenterHolder
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson

class AddIngredientActivity : AppCompatActivity(), AddIngredientContract.View, AddIngredientAdapter.OnClickListener {

    lateinit var toolbar: ActionBar
    private val recyclerView by lazy { find<RecyclerView>(R.id.rv_ingredient) }
    private val pb by lazy { find<ProgressBar>(R.id.pb_ingredient) }
    private var presenter: AddIngredientPresenter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: AddIngredientAdapter? = null
    var mPrefs: SharedPreferences? = null
    private var data: Sandwich? = null

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
        mPrefs = this.getSharedPreferences(getString(R.string.preferences_key), MODE_PRIVATE)
        data = Gson().fromJson(mPrefs?.getString(getString(R.string.preferences_data_key), ""), Sandwich::class.java)
        this.data?.extras = ArrayList<Ingredient>()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.add){
            if(data?.extras != null){
                val dataString: String? = Gson().toJson(data)
                val editor: SharedPreferences.Editor? = mPrefs?.edit()
                editor?.putString(getString(R.string.preferences_data_key), dataString)
                editor?.apply()
            }
            finish()
        }
        return super.onOptionsItemSelected(item)
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

    override fun onItemClick(data: Ingredient) {
        this.data?.extras?.add(data)
        Toast.makeText(this, data.name + " " + getString(R.string.add_text), Toast.LENGTH_SHORT).show()
    }

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
