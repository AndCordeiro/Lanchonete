package andcordeiro.com.lanchonete.histories.addingredient

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.system.extensions.makeObservable
import andcordeiro.com.lanchonete.system.retrofit.Api
import andcordeiro.com.lanchonete.system.retrofit.ApiProvider
import retrofit2.Response
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.Callable

class AddIngredientModelImpl: AddIngredientModel {


    private var lastError:String? = null
    private var lastException: Exception? = null

    override fun getLastError(): String? = lastError
    override fun getLastException(): Exception? = lastException

    private val api: Api = ApiProvider.api()

    override fun loadIngredient(): List<Ingredient> {
        var promotions = ArrayList<Ingredient>()

        try {
            val callback: Response<List<Ingredient>> =
                    api.getIngredient().execute()
            if (callback.isSuccessful) {
                promotions = callback.body() as ArrayList<Ingredient>
            }else{
                lastError = callback.message()
            }
        }catch (e: Exception){
            lastException = e
            e.printStackTrace()
        }
        return promotions
    }

    override fun loadIngredientAsync(): Observable<List<Ingredient>> {
        return makeObservable(Callable {loadIngredient()}).subscribeOn(Schedulers.computation())
    }
}