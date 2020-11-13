package com.krisbunda.gamesmart.repos

import com.google.gson.Gson
import com.krisbunda.gamesmart.model.Product
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import java.net.URL

class ProductsRepository {
    fun getAllProducts(): @NonNull Single<List<Product>>? {
        return Single.create<List<Product>> {
            it.onSuccess(fetchProducts())
        }
    }

    fun searchForProducts(term: String): Single<List<Product>>{
        return Single.create<List<Product>> {
            val filteredProducts = fetchProducts().filter { it.title!!.contains(term, true) }
            it.onSuccess(filteredProducts)
        }
    }
    fun fetchProducts(): List<Product> {
        val json = URL("https://gist.githubusercontent.com/bunda3d/a0edc1d8e6073b43fc76d195c9a57302/raw/de2290fa084a94c4b367603ee7f00d4e34e84b66/shop_products.json").readText()
        return Gson().fromJson(json, Array<Product>::class.java).toList()
    }
}