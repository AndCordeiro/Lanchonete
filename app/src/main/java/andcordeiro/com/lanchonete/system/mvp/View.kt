package andcordeiro.com.lanchonete.system.mvp

import android.content.Context
import android.content.Intent

interface View{
    fun intent(): Intent
    fun context(): Context
}