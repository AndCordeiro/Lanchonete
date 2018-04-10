package andcordeiro.com.lanchonete.system.retrofit

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Order
import andcordeiro.com.lanchonete.entities.Promotion
import andcordeiro.com.lanchonete.entities.Sandwich
import retrofit2.Call
import retrofit2.http.GET



interface Api{

    @GET("lanche")
    fun getSandwich(): Call<List<Sandwich>>

    @GET("promocao")
    fun getPromotion(): Call<List<Promotion>>

    @GET("pedido")
    fun getOrder(): Call<List<Order>>

    @GET("ingrediente")
    fun getIngredient(): Call<List<Ingredient>>
}