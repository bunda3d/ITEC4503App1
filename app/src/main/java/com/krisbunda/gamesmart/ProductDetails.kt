package com.krisbunda.gamesmart

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        val productdesc = intent.getStringExtra("proddesc")
        val photoUrl = intent.getStringExtra("photoaddress")
        val price = intent.getStringExtra("pricedol")
        val points = intent.getStringExtra("pricepts")


        product_name.text = title
        product_desc.text = productdesc
        Picasso.get().load(photoUrl).into(photo)
        price_dol.text = price
        price_pts.text = points


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