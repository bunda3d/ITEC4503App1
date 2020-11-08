package com.krisbunda.gamesmart

import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.krisbunda.gamesmart.model.ArtSuppliesFragment
import com.krisbunda.gamesmart.model.MakerKitsFragment
import com.krisbunda.gamesmart.model.Product
import com.krisbunda.gamesmart.model.PuzzlesFragment
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_cart.*

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(findViewById(R.id.toolbar))

        nav_view_cart.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navcat_home -> {
                    d("catnav", "navcat_home was pressed")
                }
                R.id.navcat_art_supplies -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navcat_host_fragment, ArtSuppliesFragment()).commit()
                    d("catnav", "navcat_art_supplies was pressed")
                }
                R.id.navcat_maker_kits -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navcat_host_fragment, MakerKitsFragment()).commit()
                    d("catnav", "navcat_maker_kits was pressed")
                }
                R.id.navcat_puzzles -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navcat_host_fragment, PuzzlesFragment()).commit()
                    d("catnav", "navcat_puzzles was pressed")
                }
            }
            it.isChecked = true
            drawer_layout_cart.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_menu)

        }

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

    //fix this w/better solution later (store hamburger functionality)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawer_layout_cart.openDrawer(GravityCompat.START)
        return true
        //return super.onOptionsItemSelected(item)
    }


}