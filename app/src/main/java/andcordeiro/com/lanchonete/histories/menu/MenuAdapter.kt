package andcordeiro.com.lanchonete.histories.menu

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.entities.Sandwich
import andcordeiro.com.lanchonete.histories.addorder.AddOrderActivity
import andcordeiro.com.lanchonete.system.extensions.find
import andcordeiro.com.lanchonete.system.extensions.loadImage
import andcordeiro.com.lanchonete.system.extensions.show
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MenuAdapter(private var className: String?, private var view: MenuContract.View, private var data: MutableList<Sandwich>, private val onClickListener: MenuAdapter.OnClickListener?) : RecyclerView.Adapter<MenuAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_menu, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = this.data[position]
        holder.tvName.text = data.name
        holder.tvIngredients.text = Ingredient.getIngredientsName(data.ingredients)
        holder.ivSandwich.loadImage(data.image)
        holder.tvPrice.text = view.priceSandwich(data.ingredients).toString()
        if(className.equals(AddOrderActivity::class.java.name)){
            holder.llCustomize.show()
            holder.btnCustomize.setOnClickListener{onClickListener?.onButtonClick(this.data[position])}
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnClickListener {
        fun onItemClick(data: Sandwich)

        fun onLongItemClick(data: Sandwich)

        fun onButtonClick(data: Sandwich)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        val llCustomize by lazy { itemView.find<LinearLayout>(R.id.ll_customize)}
        val tvName by lazy { itemView.find<TextView>(R.id.tv_name)}
        val ivSandwich by lazy { itemView.find<ImageView>(R.id.iv_sandwich) }
        val tvIngredients by lazy { itemView.find<TextView>(R.id.tv_ingredients) }
        val tvPrice by lazy { itemView.find<TextView>(R.id.tv_price) }
        val btnCustomize by lazy { itemView.find<Button>(R.id.btn_customize) }

        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickListener?.onItemClick(data[adapterPosition])
        }

        override fun onLongClick(v: View?): Boolean {
            onClickListener?.onLongItemClick(data[adapterPosition])
            return false
        }

    }


}