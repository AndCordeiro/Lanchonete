package andcordeiro.com.lanchonete.system.mvp

interface Model{
    fun getLastError(): String?
    fun getLastException(): Exception?
}