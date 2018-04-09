package andcordeiro.com.lanchonete.system.extensions

import android.support.v4.app.Fragment
import android.view.View

inline fun <reified T : View> Fragment.find(id: Int): T = view?.findViewById<T>(id) as T