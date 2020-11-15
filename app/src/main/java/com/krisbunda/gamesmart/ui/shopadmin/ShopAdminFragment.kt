package com.krisbunda.gamesmart.ui.shopadmin

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.krisbunda.gamesmart.R
import com.krisbunda.gamesmart.database.AppDatabase
import com.krisbunda.gamesmart.database.ProductData
import kotlinx.android.synthetic.main.fragment_shop_admin.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ShopAdminFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_shop_admin,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnProdUpdate.setOnClickListener {
            val title = inputProdTitle.text
            val descProd = inputProdDesc.text
            val price = inputProdPrice.text
            val points = inputProdPoints.text

            d("dbtest", "Prod Update btn pressed, with item: $descProd")

            //build db
            doAsync {

                val db: AppDatabase = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "ProductDataDb"
                ).build()

                db.productDao().insertAll(ProductData(
                    null,
                    title.toString(),
                    "https://via.placeholder.com/300/BB86FC/FFFFFF/?text=GameSmart",
                    descProd.toString(), //"ipsum in voluptate fugiat irure pariatur mollit non deserunt reprehenderit dolore id officia sit",
                    price.toString().toDoubleOrNull(),
                    points.toString().toInt()
                ))
                val products = db.productDao().getAll()

                uiThread {
                    d("dbtest", "products size? ${products.size} ${products.last().title}")
                }
            }//end build database
        }
    }
}