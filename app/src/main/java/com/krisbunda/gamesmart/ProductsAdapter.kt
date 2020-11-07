package com.krisbunda.gamesmart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krisbunda.gamesmart.ProductsAdapter.ViewHolder
import com.krisbunda.gamesmart.model.Product
import com.squareup.picasso.Picasso


//pass Products Adapter from CartActivity
class ProductsAdapter(private val products: ArrayList<Product>) : RecyclerView.Adapter<ViewHolder>() {

    //bind objects to product row layout
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(products[position].photoUrl).into(holder.image)
        holder.title.text = products[position].title
    }

    //get product row layout
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
