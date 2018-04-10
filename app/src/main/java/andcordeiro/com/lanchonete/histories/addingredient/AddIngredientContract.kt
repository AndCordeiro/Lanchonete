package andcordeiro.com.lanchonete.histories.addingredient

import andcordeiro.com.lanchonete.entities.Ingredient

interface AddIngredientContract{

    interface View: andcordeiro.com.lanchonete.system.mvp.View{
        fun ingredient(ingredients: List<Ingredient>)
    }

    interface Presenter: andcordeiro.com.lanchonete.system.mvp.Presenter {
        fun view(view: AddIngredientContract.View)
        fun loadIngredient()
    }


}