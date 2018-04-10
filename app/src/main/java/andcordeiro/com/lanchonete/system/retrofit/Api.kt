package andcordeiro.com.lanchonete.system.retrofit

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Order
import andcordeiro.com.lanchonete.entities.Promotion
import andcordeiro.com.lanchonete.entities.Sandwich
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface Api{

    @GET("lanche")
    fun getSandwich(): Call<List<Sandwich>>

    @GET("promocao")
    fun getPromotion(): Call<List<Promotion>>

    @GET("pedido")
    fun getOrder(): Call<List<Order>>

    @GET("ingrediente")
    fun getIngredient(): Call<List<Ingredient>>

    @PUT("pedido/{id_sandwich}")
    fun setOrder(@Path("id_sandwich") id: String?, @Query("extras") extras: String?): Call<Void>
}