package com.krisbunda.gamesmart.model

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.krisbunda.gamesmart.ProductDetails
import com.krisbunda.gamesmart.ProductsAdapter
import com.krisbunda.gamesmart.R
import com.krisbunda.gamesmart.repos.ProductsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.view.*
import kotlinx.android.synthetic.main.product_details.*

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

        val productsRepository = ProductsRepository().getAllProducts()
        productsRepository?.let { loadRecyclerView(it) }

        btn_search.setOnClickListener {
            loadRecyclerView(ProductsRepository().searchForProducts(search_term.text.toString()))
        }
    }

    fun loadRecyclerView(productsRepository: Single<List<Product>>) {
        val single = productsRepository
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                d("dbtest", "success subscribing to ProductsRepository")
                recycler_view.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = ProductsAdapter(it) {
                            extraTitle,
                            extraDescProd,
                            extraImageUrl,
                            extraPriceDol,
                            extraPricePts, photoView
                        ->
                        //this makes product row items clickable to product details
                        val intent = Intent(activity, ProductDetails::class.java)
                        intent.putExtra("title", extraTitle)
                        intent.putExtra("proddesc", extraDescProd)
                        intent.putExtra("photoaddress", extraImageUrl)
                        intent.putExtra("pricedol", extraPriceDol)
                        intent.putExtra("pricepts", extraPricePts)
                        val options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(
                                activity as AppCompatActivity, photoView, "photo_to_animate"
                            )
                        startActivity(intent, options.toBundle())
                    }
                }
                progressBar.visibility = View.GONE
            }, {
                d("dbtest", "error :( ${it.message}")
            })


        /* btn_search.setOnClickListener {

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
        } */
    }
}