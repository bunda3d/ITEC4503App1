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
                URL("https://next.json-generator.com/api/json/get/4ylHV_btK").readText()
            val list = Gson().fromJson(json, Array<Product>::class.java).toList()
            val product = list.first { it.title == productTitle }

            productDetails.postValue(product)
        }

    }

}