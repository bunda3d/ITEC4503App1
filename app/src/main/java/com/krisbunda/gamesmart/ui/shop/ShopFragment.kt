package com.krisbunda.gamesmart.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.krisbunda.gamesmart.ProductsAdapter
import com.krisbunda.gamesmart.R
import com.krisbunda.gamesmart.database.AppDatabase
import com.krisbunda.gamesmart.model.Product
import kotlinx.android.synthetic.main.fragment_shop.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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
                        AppDatabase::class.java, "ProductDataDb"
                ).build()

                // val productsFromDB = db.productDao().getAll()

                val searchTerm = db.productDao().searchFor("%${search_term.text}%")

                val products = searchTerm.map {
                    Product(
                            it.title,
                            it.photoUrl, //"https://via.placeholder.com/300/BB86FC/FFFFFF/?text=GameSmart",
                            it.descProd,
                            it.price,
                            it.points
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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        //initiates empty search click to populate recycler view with products
        btn_search.performClick()
    }
}