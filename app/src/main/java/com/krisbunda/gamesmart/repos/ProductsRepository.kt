package com.krisbunda.gamesmart.repos

import com.google.gson.Gson
import com.krisbunda.gamesmart.model.Product
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import java.net.URL

class ProductsRepository {
    fun getAllProducts(): @NonNull Single<List<Product>>? {
        return Single.create<List<Product>> {
            val json = URL("https://next.json-generator.com/api/json/get/4ylHV_btK").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }
}