package andcordeiro.com.lanchonete.histories.promotion

import andcordeiro.com.lanchonete.entities.Promotion
import andcordeiro.com.lanchonete.system.mvp.Model
import rx.Observable

interface PromotionModel: Model{

    fun loadPromotion(): List<Promotion>

    fun loadPromotionAsync(): Observable<List<Promotion>>
}