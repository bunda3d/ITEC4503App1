package com.krisbunda.gamesmart.cart

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.krisbunda.gamesmart.R
import com.krisbunda.gamesmart.R.*

class CartStatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(layout.activity_cart_status)
    }
}