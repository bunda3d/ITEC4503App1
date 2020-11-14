package com.krisbunda.gamesmart

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.room.Room
import com.krisbunda.gamesmart.cart.CartStatusActivity
import com.krisbunda.gamesmart.database.AppDatabase
import com.krisbunda.gamesmart.database.ProductData
import com.krisbunda.gamesmart.ui.prodcategories.ArtSuppliesFragment
import com.krisbunda.gamesmart.ui.prodcategories.MakerKitsFragment
import com.krisbunda.gamesmart.ui.prodcategories.PuzzlesFragment
import com.krisbunda.gamesmart.ui.shop.ShopFragment
import com.krisbunda.gamesmart.ui.shopadmin.ShopAdminFragment
import kotlinx.android.synthetic.main.activity_cart.*

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(findViewById(R.id.toolbar))

        //build database
        doAsync {


                val db: AppDatabase = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "ProductDataDb"
                ).build()
                //uncomment if db is deleted and need to seed starter item
                    db.productDao().insertAll(ProductData(
                        null,
                        "Build a NES Kit",
                        "https://via.placeholder.com/300/BB86FC/FFFFFF/?text=GameSmart",
                        "ipsum in voluptate fugiat irure pariatur mollit non deserunt reprehenderit dolore id officia sit",
                        65.00,
                        65000
                    ))

            val products = db.productDao().getAll()

            uiThread {
                d("dbtest", "products size? ${products.size} ${products[0].title}")
            }
        }
        //end build database

        supportFragmentManager.beginTransaction()
            .replace(R.id.navcat_host_fragment, ShopFragment()).commit()

        nav_view_cart.setNavigationItemSelectedListener {
            when (it.itemId){
                /*R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.layout.activity_cart, MainActivity()).commit()
                    d("catnav", "nav_home was pressed")
                }*/
                R.id.navcat_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navcat_host_fragment, ShopFragment()).commit()
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
                R.id.navcat_admin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navcat_host_fragment, ShopAdminFragment()).commit()
                    d("catnav", "navcat_shop_admin was pressed")
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

    }

    //fix this w/better solution later (store hamburger functionality)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_cart) {
            startActivity(Intent(this, CartStatusActivity::class.java))
            return true
            d("catnav", "nav_cart button pressed")
        }
        drawer_layout_cart.openDrawer(GravityCompat.START)
        return true
        //return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }


}