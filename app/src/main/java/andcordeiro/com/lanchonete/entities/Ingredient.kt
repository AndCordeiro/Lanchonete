package andcordeiro.com.lanchonete.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ingredient: Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("price")
    var price: Double? = null
    @SerializedName("image")
    var image: String? = null

    override fun toString(): String {
        return "Sandwich(id=$id, name=$name, price=$price, image=$image)"
    }

}