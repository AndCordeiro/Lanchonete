package andcordeiro.com.lanchonete.histories.menu

import andcordeiro.com.lanchonete.entities.Ingredient
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

class MenuPresenter(private var view : MenuContract.View) : MenuContract.Presenter{

    private var subscriptionMenu: Subscription? = null
    private val model by lazy { MenuModelImpl() }

    override fun create() {}

    override fun destroy() {
        if(subscriptionMenu != null && !subscriptionMenu!!.isUnsubscribed){
            subscriptionMenu!!.unsubscribe()
        }
    }

    override fun view(view: Object) {}

    override fun view(view: MenuContract.View) {
        this.view = view
    }

    override fun loadMenu() {
        subscriptionMenu = model.loadMenuAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({view.sandwiches(it)})
    }

    override fun priceSandwich(ingredients: List<Ingredient>?): Double? = model.priceSandwich(ingredients)




}