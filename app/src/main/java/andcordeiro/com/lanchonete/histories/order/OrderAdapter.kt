package andcordeiro.com.lanchonete.histories.order

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Order
import andcordeiro.com.lanchonete.system.extensions.find
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class OrderAdapter(private var data: MutableList<Order>, private val onClickListener: OrderAdapter.OnClickListener?) : RecyclerView.Adapter<OrderAdapter.Holder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_order, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = this.data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnClickListener {
        fun onItemClick(data: Order)

        fun onLongItemClick(data: Order)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        val tvName by lazy { itemView.find<TextView>(R.id.tv_name)}
        val tvDescription by lazy { itemView.find<TextView>(R.id.tv_description) }

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