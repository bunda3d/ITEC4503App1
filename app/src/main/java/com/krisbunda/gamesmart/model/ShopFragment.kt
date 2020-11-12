package com.krisbunda.gamesmart.model

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Dao
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.krisbunda.gamesmart.ProductsAdapter
import com.krisbunda.gamesmart.R
import com.krisbunda.gamesmart.database.AppDatabase
import com.krisbunda.gamesmart.database.ProductData
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class ShopFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_shop, container, false)

        //use json -> gson to populate prod data
        /*doAsync {
            val json = URL("https://next.json-generator.com/api/json/get/4ylHV_btK").readText()

            uiThread {
                d("threads", "json: $json")
                val products = Gson().fromJson(json, Array<Product>::class.java).toList()

                root.recycler_view.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = ProductsAdapter(products)
                    root.progressBar.visibility = View.GONE
                }

            }
        }*/

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_search.setOnClickListener {

            doAsync {

                val db = Room.databaseBuilder(
                        activity!!.applicationContext,
                        AppDatabase::class.java, "ProdDataDb"
                ).build()

                // val productsFromDB = db.productDao().getAll()

                val searchTerm = db.productDao().searchFor("%${search_term.text}%")

                val products = searchTerm.map {
                    Product(
                            it.title,
                            "https://via.placeholder.com/300/BB86FC/FFFFFF/?text=GameSmart",
                            it.descProd,
                            56.00,
                            5600
                    )
                }

                uiThread {
                    recycler_view.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = ProductsAdapter(products)

                    }
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}