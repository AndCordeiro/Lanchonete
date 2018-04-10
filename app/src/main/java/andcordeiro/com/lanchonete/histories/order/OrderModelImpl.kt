package andcordeiro.com.lanchonete.histories.order

import andcordeiro.com.lanchonete.entities.Order
import andcordeiro.com.lanchonete.system.extensions.makeObservable
import andcordeiro.com.lanchonete.system.retrofit.Api
import andcordeiro.com.lanchonete.system.retrofit.ApiProvider
import retrofit2.Response
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.Callable

class OrderModelImpl: OrderModel {


    private var lastError:String? = null
    private var lastException: Exception? = null

    override fun getLastError(): String? = lastError
    override fun getLastException(): Exception? = lastException

    private val api: Api = ApiProvider.api()

    override fun loadOrder(): List<Order> {
        var orders = ArrayList<Order>()

        try {
            val callback: Response<List<Order>> =
                    api.getOrder().execute()
            if (callback.isSuccessful) {
                orders = callback.body() as ArrayList<Order>
            }else{
                lastError = callback.message()
            }
        }catch (e: Exception){
            lastException = e
            e.printStackTrace()
        }
        return orders
    }

    override fun loadOrderAsync(): Observable<List<Order>> {
        return makeObservable(Callable {loadOrder()}).subscribeOn(Schedulers.computation())
    }
}