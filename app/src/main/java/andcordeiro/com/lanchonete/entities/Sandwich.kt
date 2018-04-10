package andcordeiro.com.lanchonete.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Sandwich: Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("ingredients")
    var ingredients: MutableList<Ingredient>? = null
    @SerializedName("image")
    var image: String? = null
    var price: Double? = null

    override fun toString(): String {
        return "Sandwich(id=$id, name=$name, ingredients=$ingredients, image=$image, price=$price)"
    }
}