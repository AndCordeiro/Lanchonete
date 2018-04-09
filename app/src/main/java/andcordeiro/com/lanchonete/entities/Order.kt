package andcordeiro.com.lanchonete.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Order: Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("ingredients")
    var ingredients: MutableList<Int>? = null
    @SerializedName("image")
    var image: String? = null

    override fun toString(): String {
        return "Sandwich(id=$id, name=$name, ingredients=$ingredients, image=$image)"
    }

}