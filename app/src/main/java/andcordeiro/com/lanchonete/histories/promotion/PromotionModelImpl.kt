package andcordeiro.com.lanchonete.histories.promotion

import andcordeiro.com.lanchonete.entities.Promotion
import andcordeiro.com.lanchonete.system.extensions.makeObservable
import andcordeiro.com.lanchonete.system.retrofit.Api
import andcordeiro.com.lanchonete.system.retrofit.ApiProvider
import retrofit2.Response
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.Callable

class PromotionModelImpl: PromotionModel {


    private var lastError:String? = null
    private var lastException: Exception? = null

    override fun getLastError(): String? = lastError
    override fun getLastException(): Exception? = lastException

    private val api: Api = ApiProvider.api()

    override fun loadPromotion(): List<Promotion> {
        var promotions = ArrayList<Promotion>()

        try {
            val callback: Response<List<Promotion>> =
                    api.getPromotion().execute()
            if (callback.isSuccessful) {
                promotions = callback.body() as ArrayList<Promotion>
            }else{
                lastError = callback.message()
            }
        }catch (e: Exception){
            lastException = e
            e.printStackTrace()
        }
        return promotions
    }

    override fun loadPromotionAsync(): Observable<List<Promotion>> {
        return makeObservable(Callable {loadPromotion()}).subscribeOn(Schedulers.computation())
    }
}