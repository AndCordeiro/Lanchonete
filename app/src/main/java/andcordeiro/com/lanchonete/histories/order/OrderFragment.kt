package andcordeiro.com.lanchonete.histories.order

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Order
import andcordeiro.com.lanchonete.histories.addorder.AddOrderActivity
import andcordeiro.com.lanchonete.system.extensions.find
import andcordeiro.com.lanchonete.system.extensions.gone
import andcordeiro.com.lanchonete.system.extensions.show
import andcordeiro.com.lanchonete.system.mvp.PresenterHolder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

class OrderFragment : Fragment(), OrderContract.View, OrderAdapter.OnClickListener {

    private val recyclerView by lazy { find<RecyclerView>(R.id.rv_order)  }
    private val pb by lazy { find<ProgressBar>(R.id.pb_order) }
    private var presenter: OrderPresenter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: OrderAdapter? = null
    private val fab by lazy { find<FloatingActionButton>(R.id.fab) }

    companion object {
        fun newInstance(): OrderFragment = OrderFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_order, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter()
        presenter?.create()
        initViews()
    }

    private fun initViews() {
        layoutManager = LinearLayoutManager(context())
        recyclerView.layoutManager = layoutManager
        fab.bringToFront()
        fab.setOnClickListener { startActivity(Intent(context(), AddOrderActivity::class.java)) }
        loadOrder()
        pb.show()
    }

    private fun loadOrder(){
        presenter?.loadOrder()
    }

    override fun orders(orders: List<Order>) {
        adapter = OrderAdapter(orders as MutableList<Order>, this)
        recyclerView.adapter = adapter
        pb.gone()
    }

    override fun intent(): Intent = activity!!.intent

    override fun context(): Context = activity!!.baseContext

    override fun onItemClick(data: Order) {}

    override fun onLongItemClick(data: Order) {}

    private fun createPresenter(): OrderPresenter {
        presenter = PresenterHolder.instance!!.getPresenter(OrderPresenter::class.java)
        if (presenter == null) {
            presenter = OrderPresenter(this)
            PresenterHolder.instance?.putPresenter(OrderPresenter::class.java, presenter!!)
        } else {
            presenter?.view(this)
        }
        return presenter as OrderPresenter
    }
}
