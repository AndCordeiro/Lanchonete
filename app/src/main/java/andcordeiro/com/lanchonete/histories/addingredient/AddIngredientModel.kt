package andcordeiro.com.lanchonete.histories.addingredient

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.system.mvp.Model
import rx.Observable

interface AddIngredientModel: Model{

    fun loadIngredient(): List<Ingredient>

    fun loadIngredientAsync(): Observable<List<Ingredient>>
}