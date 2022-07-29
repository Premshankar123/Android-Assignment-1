package chalkup.sekhontech.androidassignment_1.adapters

import chalkup.sekhontech.androidassignment_1.models.ProductModel
import androidx.recyclerview.widget.RecyclerView
import chalkup.sekhontech.androidassignment_1.adapters.ProductAdapter.myviewholder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import chalkup.sekhontech.androidassignment_1.R
import com.bumptech.glide.Glide
import android.widget.TextView
import java.text.DecimalFormat

class ProductAdapter(var productList: List<ProductModel>?) : RecyclerView.Adapter<myviewholder>() {
    fun updatemovielist(list: List<ProductModel>?) {
        productList = list
        notifyDataSetChanged()
    }
    // method for filtering our recyclerview items.
    fun filterList(filterllist: List<ProductModel?>) {
        // below line is to add our filtered
        // list in our course array list.
        productList = filterllist as List<ProductModel>
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return myviewholder(view)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val price= productList!![position].price
        val price1=price.toFloat()
        val df = DecimalFormat("#.##")
        holder.product_name.text = productList!![position].product_name
        holder.price.text ="₹"+ df.format(price1).toString()
        holder.product_type.text = productList!![position].product_type
        holder.tax.text = "Tax:"+productList!![position].tax+"%"
        val image=productList!![position].image
        if(image=="") {
          holder.img.setImageResource(R.drawable.product_icon)
        }else{
            Glide.with(holder.img.context).load(image).into(holder.img)
        }
    }

    override fun getItemCount(): Int {
        return if (productList != null) productList!!.size else 0
    }

    inner class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var price: TextView
        var product_name: TextView
        var product_type: TextView
        var tax: TextView
        var img: ImageView

        init {
            product_name = itemView.findViewById(R.id.product_name)
            price = itemView.findViewById(R.id.price)
            product_type = itemView.findViewById(R.id.product_type)
            tax = itemView.findViewById(R.id.tax)
            img = itemView.findViewById(R.id.productImg)
        }
    }
}