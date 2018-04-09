package andcordeiro.com.lanchonete.system.mvp

import java.util.*

class PresenterHolder private constructor() {

    private val presenterMap: MutableMap<Class<*>, Presenter>

    init {
        this.presenterMap = HashMap()
    }

    fun putPresenter(c: Class<*>, p: Presenter) {
        presenterMap[c] = p
    }

    fun <T : Presenter> getPresenter(c: Class<*>): T? {
        if(presenterMap[c] !== null){
            return presenterMap[c] as T
        }
        return null
    }

    fun remove(c: Class<*>) {
        presenterMap.remove(c)
    }

    companion object {
        @Volatile
        internal var singleton: PresenterHolder? = null


        val instance: PresenterHolder?
            get() {
                if (singleton == null) {
                    synchronized(PresenterHolder::class.java) {
                        if (singleton == null) {
                            singleton = PresenterHolder()
                        }
                    }
                }
                return singleton
            }
    }
}
