package andcordeiro.com.lanchonete.histories.promotion

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Promotion
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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

class PromotionFragment : Fragment(), PromotionContract.View, PromotionAdapter.OnClickListener {

    private val recyclerView by lazy { find<RecyclerView>(R.id.rv_promotion)  }
    private val pb by lazy { find<ProgressBar>(R.id.pb_promotion) }
    private var presenter: PromotionPresenter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: PromotionAdapter? = null

    companion object {
        fun newInstance(): PromotionFragment = PromotionFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_promotion, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter()
        presenter?.create()
        initViews()
    }

    private fun initViews() {
        layoutManager = LinearLayoutManager(context())
        recyclerView.layoutManager = layoutManager
        loadPromotion()
        pb.show()
    }

    private fun loadPromotion(){
        presenter?.loadPromotion()
    }

    override fun promotions(promotions: List<Promotion>) {
        Log.e("teste", promotions.toString())
        adapter = PromotionAdapter(promotions as MutableList<Promotion>, this)
        recyclerView.adapter = adapter
        pb.gone()
    }

    override fun intent(): Intent = activity!!.intent

    override fun context(): Context = activity!!.baseContext

    override fun onItemClick(data: Promotion) {}

    override fun onLongItemClick(data: Promotion) {}

    private fun createPresenter(): PromotionPresenter {
        presenter = PresenterHolder.instance!!.getPresenter(PromotionPresenter::class.java)
        if (presenter == null) {
            presenter = PromotionPresenter(this)
            PresenterHolder.instance?.putPresenter(PromotionPresenter::class.java, presenter!!)
        } else {
            presenter?.view(this)
        }
        return presenter as PromotionPresenter
    }
}
