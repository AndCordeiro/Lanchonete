package andcordeiro.com.lanchonete.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Promotion: Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null

    override fun toString(): String {
        return "Promotion(id=$id, name=$name, description=$description)"
    }

}