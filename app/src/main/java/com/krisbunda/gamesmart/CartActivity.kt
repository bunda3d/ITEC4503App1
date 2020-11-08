package com.krisbunda.gamesmart

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.krisbunda.gamesmart.model.Product

import kotlinx.android.synthetic.main.content_cart.*

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(findViewById(R.id.toolbar))

        //initialize the array of product data
        val products = arrayListOf<Product>()

        //100 products
        for (i in 1..100) {
            products.add(Product(
                "Rubik's Cube Puzzle #$i",
                "https://via.placeholder.com/300/BB86FC/FFFFFF/?text=GameSmart",
                12.00,
                1200
            ))
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = ProductsAdapter(products)
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}