package andcordeiro.com.lanchonete.histories.promotion

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

class PromotionPresenter(private var view : PromotionContract.View) : PromotionContract.Presenter{

    private var subscriptionMenu: Subscription? = null
    private val model by lazy { PromotionModelImpl() }

    override fun create() {}

    override fun destroy() {
        if(subscriptionMenu != null && !subscriptionMenu!!.isUnsubscribed){
            subscriptionMenu!!.unsubscribe()
        }
    }

    override fun view(view: Object) {}

    override fun view(view: PromotionContract.View) {
        this.view = view
    }

    override fun loadPromotion() {
        subscriptionMenu = model.loadPromotionAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({view.promotions(it); model.getLastException()})
    }
}