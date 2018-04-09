package andcordeiro.com.lanchonete.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Order: Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("id_sandwich")
    var idSandwich: Int? = null
    @SerializedName("extras")
    var extras: MutableList<Int>? = null

    override fun toString(): String {
        return "Order(id=$id, idSandwich=$idSandwich, extras=$extras)"
    }
}