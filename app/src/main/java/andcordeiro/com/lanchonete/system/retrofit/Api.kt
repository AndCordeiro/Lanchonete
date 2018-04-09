package andcordeiro.com.lanchonete.system.retrofit

import andcordeiro.com.lanchonete.entities.Sandwich
import retrofit2.Call
import retrofit2.http.GET



interface Api{

    @GET("lanche")
    fun getSandwich(): Call<List<Sandwich>>
}