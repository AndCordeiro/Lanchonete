package andcordeiro.com.lanchonete.system.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiProvider {
    companion object {
        fun api():Api{
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val api = retrofit.create(Api::class.java)
            return api
        }
    }
}