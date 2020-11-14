package com.krisbunda.gamesmart

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krisbunda.gamesmart.model.Product
import com.krisbunda.gamesmart.ui.productdetails.ProductDetails
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt


//pass Products Adapter from CartActivity
class ProductsAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    //bind objects to product row layout
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.title
        Picasso.get().load(product.photoUrl).into(holder.image)
        holder.descprod.text = product.descProd
        holder.pricedol.text = "$${product.price?.roundToInt()}"
        holder.pricepts.text = "${product.points}pts"

    }

    //get product row layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        //this makes product row items clickable to product details
        view.setOnClickListener {
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("title",products[holder.adapterPosition].title)
            intent.putExtra("photoaddress",products[holder.adapterPosition].photoUrl)
            intent.putExtra("proddesc",products[holder.adapterPosition].descProd)
            intent.putExtra("pricedol",products[holder.adapterPosition].price?.roundToInt().toString())
            intent.putExtra("pricepts",products[holder.adapterPosition].points.toString())

            parent.context.startActivity(intent)
        }
        return holder
    }

    //number of products
    override fun getItemCount() = products.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.photo)
        val descprod: TextView = itemView.findViewById(R.id.description)
        val pricedol: TextView = itemView.findViewById(R.id.price)
        val pricepts: TextView = itemView.findViewById(R.id.points)
    }
}
