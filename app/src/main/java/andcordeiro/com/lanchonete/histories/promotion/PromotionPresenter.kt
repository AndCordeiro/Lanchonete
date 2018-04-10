package andcordeiro.com.lanchonete.histories.promotion

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

class PromotionPresenter(private var view : PromotionContract.View) : PromotionContract.Presenter{

    private var subscriptionPromotion: Subscription? = null
    private val model by lazy { PromotionModelImpl() }

    override fun create() {}

    override fun destroy() {
        if(subscriptionPromotion != null && !subscriptionPromotion!!.isUnsubscribed){
            subscriptionPromotion!!.unsubscribe()
        }
    }

    override fun view(view: Object) {}

    override fun view(view: PromotionContract.View) {
        this.view = view
    }

    override fun loadPromotion() {
        subscriptionPromotion = model.loadPromotionAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({view.promotions(it); model.getLastException()})
    }
}