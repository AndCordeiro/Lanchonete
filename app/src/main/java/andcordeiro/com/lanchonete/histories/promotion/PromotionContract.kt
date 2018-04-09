package andcordeiro.com.lanchonete.histories.promotion

import andcordeiro.com.lanchonete.entities.Promotion

interface PromotionContract{

    interface View: andcordeiro.com.lanchonete.system.mvp.View{
        fun promotions(promotions: List<Promotion>)
    }

    interface Presenter: andcordeiro.com.lanchonete.system.mvp.Presenter {
        fun view(view: PromotionContract.View)
        fun loadPromotion()
    }


}