package andcordeiro.com.lanchonete.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Order: Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("id_sandwich")
    var idSandwich: Int? = null
    @SerializedName("sandwich")
    var sandwich: Sandwich? = null
    @SerializedName("extras")
    var extras: MutableList<Ingredient>? = null

    override fun toString(): String {
        return "Order(id=$id, idSandwich=$idSandwich, sandwich=$sandwich, extras=$extras)"
    }

}