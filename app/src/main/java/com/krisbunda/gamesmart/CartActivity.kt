package com.krisbunda.gamesmart

import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.krisbunda.gamesmart.model.*
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_cart.*
import kotlinx.android.synthetic.main.fragment_shop.*

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(findViewById(R.id.toolbar))

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
        drawer_layout_cart.openDrawer(GravityCompat.START)
        return true
        //return super.onOptionsItemSelected(item)
    }


}