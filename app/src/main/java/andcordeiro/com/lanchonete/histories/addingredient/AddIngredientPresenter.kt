package andcordeiro.com.lanchonete.histories.addingredient

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

class AddIngredientPresenter(private var view : AddIngredientContract.View) : AddIngredientContract.Presenter{

    private var subscriptionIngredient: Subscription? = null
    private val model by lazy { AddIngredientModelImpl() }

    override fun create() {}

    override fun destroy() {
        if(subscriptionIngredient != null && !subscriptionIngredient!!.isUnsubscribed){
            subscriptionIngredient!!.unsubscribe()
        }
    }

    override fun view(view: Object) {}

    override fun view(view: AddIngredientContract.View) {
        this.view = view
    }

    override fun loadIngredient() {
        subscriptionIngredient = model.loadIngredientAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({view.ingredient(it); model.getLastException()})
    }
}