package andcordeiro.com.lanchonete.histories.order

import andcordeiro.com.lanchonete.entities.Order
import andcordeiro.com.lanchonete.system.mvp.Model
import rx.Observable

interface OrderModel: Model{

    fun loadOrder(): List<Order>

    fun loadOrderAsync(): Observable<List<Order>>
}