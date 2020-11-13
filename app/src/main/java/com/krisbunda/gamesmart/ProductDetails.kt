package com.krisbunda.gamesmart

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.krisbunda.gamesmart.productdetails.ProductDetailsViewModel
import com.krisbunda.gamesmart.repos.ProductsRepository
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

import kotlinx.android.synthetic.main.product_details.*
import java.util.Optional.of
import kotlin.math.roundToInt

class ProductDetails : AppCompatActivity() {

    lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        viewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)

        //if title is null, search for "" (blank)
        val title = intent.getStringExtra("title") ?: ""
        val productdesc = intent.getStringExtra("proddesc")
        //val photoUrl = intent.getStringExtra("photoaddress")
        val price = intent.getStringExtra("pricedol")
        val points = intent.getStringExtra("pricepts")

        viewModel.productDetails.observe(this, Observer {
            product_name.text = it.title
            product_desc.text = "${it.descProd}"
            Picasso.get().load(it.photoUrl).into(photo)
            price_dol.text = "$${it.price?.roundToInt()}"
            price_pts.text = "${it.points}pts"
        })

        viewModel.fetchProductDetails(title)

        btn_add_to_cart.setOnClickListener{

        }



/*
        val product = ProductsRepository().getProductByName(title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                product_name.text = it.title
                product_desc.text = "${it.descProd}"
                Picasso.get().load(it.photoUrl).into(photo)
                price_dol.text = "$${it.price?.roundToInt()}"
                price_pts.text = "${it.points}pts"
            }, {})
*/


        availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("$title is in stock!")
                .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                    }
                })
                .create()
                .show()
        }
    }
}