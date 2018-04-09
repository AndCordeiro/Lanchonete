package andcordeiro.com.lanchonete.histories.promotion

import andcordeiro.com.lanchonete.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PromotionFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_promotion, container, false)

    companion object {
        fun newInstance(): PromotionFragment = PromotionFragment()
    }
}
