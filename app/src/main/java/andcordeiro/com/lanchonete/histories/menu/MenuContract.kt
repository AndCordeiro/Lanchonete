package andcordeiro.com.lanchonete.histories.menu

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Sandwich

interface MenuContract{

    interface View: andcordeiro.com.lanchonete.system.mvp.View{
        fun sandwiches(sandwiches: List<Sandwich>)
        fun priceSandwich(ingredients: List<Ingredient>?): Double?
    }

    interface Presenter: andcordeiro.com.lanchonete.system.mvp.Presenter {
        fun view(view: MenuContract.View)
        fun priceSandwich(ingredients: List<Ingredient>?): Double?
        fun loadMenu()
    }
}