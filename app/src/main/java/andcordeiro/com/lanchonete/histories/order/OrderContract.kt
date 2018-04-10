package andcordeiro.com.lanchonete.histories.order

import andcordeiro.com.lanchonete.entities.Order

interface OrderContract{

    interface View: andcordeiro.com.lanchonete.system.mvp.View{
        fun orders(promotions: List<Order>)
    }

    interface Presenter: andcordeiro.com.lanchonete.system.mvp.Presenter {
        fun view(view: OrderContract.View)
        fun loadOrder()
    }


}