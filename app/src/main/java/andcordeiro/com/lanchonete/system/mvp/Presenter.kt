package andcordeiro.com.lanchonete.system.mvp

interface Presenter{
    fun create()
    fun destroy()
    fun view(view: Object)
}