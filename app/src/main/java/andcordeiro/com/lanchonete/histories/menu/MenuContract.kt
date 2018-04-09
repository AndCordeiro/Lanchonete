package andcordeiro.com.lanchonete.histories.menu

import andcordeiro.com.lanchonete.entities.Sandwich

interface MenuContract{

    interface View: andcordeiro.com.lanchonete.system.mvp.View{
        fun sandwiches(sandwiches: List<Sandwich>)
    }

    interface Presenter: andcordeiro.com.lanchonete.system.mvp.Presenter {
        fun view(view: MenuContract.View)
        fun loadMenu()
    }
}