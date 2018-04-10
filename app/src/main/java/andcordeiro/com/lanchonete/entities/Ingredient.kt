package andcordeiro.com.lanchonete.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ingredient(idIngredient: Int, nameIngredient: String, priceIngredient: Double, imageIngredient: String?): Serializable {



    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("price")
    var price: Double? = null
    @SerializedName("image")
    var image: String? = null

    init {
        id = idIngredient
        name = nameIngredient
        price = priceIngredient
        image = imageIngredient
    }

    override fun toString(): String {
        return "Ingredient(id=$id, name=$name, price=$price, image=$image)"
    }

    companion object {
        fun getIngredientsName(ingredients: List<Ingredient>?): String{
            val nameIngredients = StringBuilder()
            ingredients?.forEach { ingredient: Ingredient? ->
                nameIngredients.append(ingredient?.name).append(", ")
            }
            if (nameIngredients.isNotEmpty()) {
                nameIngredients.deleteCharAt(nameIngredients.length - 2)
            }
            return nameIngredients.toString()
        }
    }
}