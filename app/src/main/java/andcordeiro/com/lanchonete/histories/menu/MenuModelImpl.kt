package andcordeiro.com.lanchonete.histories.menu

import andcordeiro.com.lanchonete.entities.Sandwich
import andcordeiro.com.lanchonete.system.extensions.makeObservable
import andcordeiro.com.lanchonete.system.retrofit.Api
import andcordeiro.com.lanchonete.system.retrofit.ApiProvider
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

}