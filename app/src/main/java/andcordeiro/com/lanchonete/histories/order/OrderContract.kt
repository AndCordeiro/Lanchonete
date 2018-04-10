package andcordeiro.com.lanchonete.histories.order

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Order

interface OrderContract{

    interface View: andcordeiro.com.lanchonete.system.mvp.View{
        fun orders(promotions: List<Order>)
        fun priceSandwich(ingredients: List<Ingredient>?): Double?
    }

    interface Presenter: andcordeiro.com.lanchonete.system.mvp.Presenter {
        fun view(view: OrderContract.View)
        fun priceSandwich(ingredients: List<Ingredient>?): Double?
        fun loadOrder()
    }


}