package andcordeiro.com.lanchonete.system.extensions

import android.util.Log
import rx.Observable
import java.util.concurrent.Callable


fun <T> makeObservable(func: Callable<T>): Observable<T> {
    return Observable.create({ subscriber ->
        try {
            subscriber.onNext(func.call())
        } catch (ex: Exception) {
            Log.e("RX", "RxError: ", ex)
        }
    })
}