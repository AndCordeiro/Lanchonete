package andcordeiro.com.lanchonete.system.extensions

import andcordeiro.com.lanchonete.R
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(image: String?) {
    Glide.with(this.context)
            .load(image)
            .centerCrop()
            .error(ContextCompat.getDrawable(this.context, R.mipmap.ic_launcher))
            .placeholder(ContextCompat.getDrawable(this.context, R.mipmap.ic_launcher))
            .crossFade()
            .fitCenter()
            .into(this)
}

