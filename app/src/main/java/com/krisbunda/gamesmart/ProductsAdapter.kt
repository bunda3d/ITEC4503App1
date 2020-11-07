package com.krisbunda.gamesmart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krisbunda.gamesmart.ProductsAdapter.ViewHolder
import com.krisbunda.gamesmart.model.Product

//pass Products Adapter from CartActivity
class ProductsAdapter(private val products: ArrayList<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    //title holder
    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        holder.title.text = products[position].title
    }

    //photo holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(view)
    }

    //number of products
    override fun getItemCount() = products.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
    }
}
