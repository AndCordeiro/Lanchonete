package andcordeiro.com.lanchonete.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Promotion: Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("id_sandwich")
    var idSandwich: String? = null
    @SerializedName("extras")
    var extras: MutableList<Int>? = null

    override fun toString(): String {
        return "Promotion(id=$id, idSandwich=$idSandwich, extras=$extras)"
    }

}