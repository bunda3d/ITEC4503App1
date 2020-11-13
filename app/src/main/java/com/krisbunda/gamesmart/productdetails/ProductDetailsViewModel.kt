package com.krisbunda.gamesmart.productdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.krisbunda.gamesmart.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ProductDetailsViewModel : ViewModel() {

    val productDetails = MutableLiveData<Product>()

    fun fetchProductDetails(productTitle: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val json =
                URL("https://gist.githubusercontent.com/bunda3d/a0edc1d8e6073b43fc76d195c9a57302/raw/de2290fa084a94c4b367603ee7f00d4e34e84b66/shop_products.json").readText()
            val list = Gson().fromJson(json, Array<Product>::class.java).toList()
            val product = list.first { it.title == productTitle }

            productDetails.postValue(product)
        }

    }

}