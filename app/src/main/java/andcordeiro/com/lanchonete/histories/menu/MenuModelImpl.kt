package andcordeiro.com.lanchonete.histories.menu

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Sandwich
import andcordeiro.com.lanchonete.system.extensions.makeObservable
import andcordeiro.com.lanchonete.system.retrofit.Api
import andcordeiro.com.lanchonete.system.retrofit.ApiProvider
import com.google.gson.Gson
import retrofit2.Response
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.Callable


class MenuModelImpl: MenuModel {

    private var lastError:String? = null
    private var lastException: Exception? = null

    override fun getLastError(): String? = lastError
    override fun getLastException(): Exception? = lastException

    private val api: Api = ApiProvider.api()

    override fun loadMenu(): List<Sandwich> {
        var sandwiches = ArrayList<Sandwich>()

        try {
            val callback: Response<List<Sandwich>> =
                    api.getSandwich().execute()
            if (callback.isSuccessful) {
                sandwiches = callback.body() as ArrayList<Sandwich>
            }else{
                lastError = callback.message()
            }
        }catch (e: Exception){
            lastException = e
            e.printStackTrace()
        }
        return sandwiches
    }

    override fun loadMenuAsync(): Observable<List<Sandwich>> {
        return makeObservable(Callable {loadMenu()}).subscribeOn(Schedulers.computation())
    }

    override fun setOrder(sandwich: Sandwich): Int? {
        try {
            var extras = ArrayList<Ingredient>()
            sandwich.extras?.forEach(){
                extras.add(it)
            }
            val callback= api.setOrder(sandwich.id.toString(), Gson().toJson(extras)).execute()
            if (callback.isSuccessful) {
                callback.message()
            }else{
                lastError = callback.message()
            }
        }catch (e: Exception){
            lastException = e
            e.printStackTrace()
        }
        return 1
    }

    override fun setOrderAsync(sandwich: Sandwich): Observable<Int>? {
        return makeObservable(Callable {setOrder(sandwich)!!}).subscribeOn(Schedulers.computation())
    }

    override fun priceSandwich(ingredients: List<Ingredient>?): Double? {
        var price = 0.0
        var countA = 0
        var countB = 0
        var countC = 0
        var countQ = 0

        ingredients?.forEach { ingredient: Ingredient? ->
            price += ingredient?.price!!
            when(ingredient.id){
                1 -> countA++
                2 -> countB++
                3 -> countC++
                5 -> countQ++
            }
        }

        if(countC >= 3){
            price = (price - (countC * 3)) + ((countC - (countC/3)) * 3 )
        }

        if(countQ >= 3){
            price = (price - (countQ * 1.5)) + ((countQ - (countQ/3)) * 1.5 )
        }

        if(countA > 0 && countB == 0){
            price = price - (price * .10)
        }

        return price
    }


}