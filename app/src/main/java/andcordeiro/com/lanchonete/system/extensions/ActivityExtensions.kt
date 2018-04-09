package andcordeiro.com.lanchonete.system.extensions

import android.app.Activity
import android.view.View


inline fun <reified T : View> Activity.find(id: Int): T = findViewById(id)