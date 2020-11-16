package com.krisbunda.gamesmart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.krisbunda.gamesmart.cart.CartStatusActivity
import com.krisbunda.gamesmart.database.AppDatabase
import com.krisbunda.gamesmart.database.ProductData
import com.krisbunda.gamesmart.ui.prodcategories.ArtSuppliesFragment
import com.krisbunda.gamesmart.ui.prodcategories.MakerKitsFragment
import com.krisbunda.gamesmart.ui.prodcategories.PuzzlesFragment
import com.krisbunda.gamesmart.ui.shop.ShopFragment
import com.krisbunda.gamesmart.ui.shopadmin.ShopAdminFragment
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.fragment_shop.*

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.editText
import org.jetbrains.anko.inputMethodManager
import org.jetbrains.anko.uiThread

class CartActivity : AppCompatActivity() {

    private lateinit var appBarCartConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        /*// Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.*/
        actionBar?.hide()

        /*
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
*/

        setSupportActionBar(findViewById(R.id.toolbar_cart))

        val drawerLayoutCart: DrawerLayout = findViewById(R.id.drawer_layout_cart)
        val navViewCart: NavigationView = findViewById(R.id.nav_view_cart)

        val navHostFragmentCart = supportFragmentManager
            .findFragmentById(R.id.navcat_host_fragment) as NavHostFragment
        val navControllerCart = navHostFragmentCart.navController
        //no bottom nav in shop yet
        /*findViewById<BottomNavigationView>(R.id.bottom_nav_view_cart)
            .setupWithNavController(navControllerCart)*/
        appBarCartConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home,
            R.id.navcat_home,
            R.id.navcat_art_supplies,
            R.id.navcat_maker_kits,
            R.id.navcat_puzzles,
            R.id.navcat_admin
        ), drawerLayoutCart)
        setupActionBarWithNavController(navControllerCart, appBarCartConfiguration)
        navViewCart.setupWithNavController(navControllerCart)



        /*

        supportFragmentManager.beginTransaction()
            .replace(R.id.navcat_host_fragment, ShopFragment()).commit()

        nav_view_cart.setNavigationItemSelectedListener {
            when (it.itemId){
                */
                /*R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.layout.activity_cart, MainActivity()).commit()
                    d("catnav", "nav_home was pressed")
                }*//*

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
        */

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_menu)
        }

        //search fab focus on search edittext input
        val btnFabSearch: FloatingActionButton = findViewById(R.id.btn_fab_search)
        btnFabSearch.setOnClickListener{
            search_term.requestFocus()
            search_term.showKeyboard()
        }

    }
    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

}