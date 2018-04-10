package andcordeiro.com.lanchonete.histories.menu

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Sandwich
import andcordeiro.com.lanchonete.system.mvp.Model
import rx.Observable

interface MenuModel: Model{

    fun loadMenu(): List<Sandwich>

    fun loadMenuAsync(): Observable<List<Sandwich>>

    fun priceSandwich(ingredients: List<Ingredient>?): Double?

}