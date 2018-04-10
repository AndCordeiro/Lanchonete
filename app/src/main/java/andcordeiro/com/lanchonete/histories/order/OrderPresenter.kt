package andcordeiro.com.lanchonete.histories.order

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

class OrderPresenter(private var view : OrderContract.View) : OrderContract.Presenter{

    private var subscriptionOrder: Subscription? = null
    private val model by lazy { OrderModelImpl() }

    override fun create() {}

    override fun destroy() {
        if(subscriptionOrder != null && !subscriptionOrder!!.isUnsubscribed){
            subscriptionOrder!!.unsubscribe()
        }
    }

    override fun view(view: Object) {}

    override fun view(view: OrderContract.View) {
        this.view = view
    }

    override fun loadOrder() {
        subscriptionOrder = model.loadOrderAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({view.orders(it); model.getLastException()})
    }
}