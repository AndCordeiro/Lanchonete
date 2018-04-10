package andcordeiro.com.lanchonete.histories.addingredient

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.system.extensions.find
import andcordeiro.com.lanchonete.system.extensions.loadImage
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AddIngredientAdapter(private var data: MutableList<Ingredient>, private val onClickListener: AddIngredientAdapter.OnClickListener?) : RecyclerView.Adapter<AddIngredientAdapter.Holder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_ingredient, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = this.data[position]
        holder.tvName.text = data.name
        holder.tvPrice.text = data.price.toString()
        holder.ivIngredient.loadImage(data.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnClickListener {
        fun onItemClick(data: Ingredient)

        fun onLongItemClick(data: Ingredient)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        val tvName by lazy { itemView.find<TextView>(R.id.tv_name)}
        val tvPrice by lazy { itemView.find<TextView>(R.id.tv_price)}
        val ivIngredient by lazy { itemView.find<ImageView>(R.id.iv_ingredient)}

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