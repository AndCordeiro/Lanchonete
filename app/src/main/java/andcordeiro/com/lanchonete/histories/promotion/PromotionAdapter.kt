package andcordeiro.com.lanchonete.histories.promotion

import andcordeiro.com.lanchonete.R
import andcordeiro.com.lanchonete.entities.Promotion
import andcordeiro.com.lanchonete.system.extensions.find
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PromotionAdapter(private var data: MutableList<Promotion>, private val onClickListener: PromotionAdapter.OnClickListener?) : RecyclerView.Adapter<PromotionAdapter.Holder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_promotion, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = this.data[position]
        holder.tvName.text = data.name
        holder.tvDescription.text = data.description
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnClickListener {
        fun onItemClick(data: Promotion)

        fun onLongItemClick(data: Promotion)
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