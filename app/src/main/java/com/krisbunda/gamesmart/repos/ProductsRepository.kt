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

    fun getProductByName(name: String): Single<Product> {
        return Single.create<Product> {
            val product = fetchProducts().first { it.title == name }
            it.onSuccess(product)
        }
    }

    fun fetchProducts(): List<Product> {
        val json = URL("https://next.json-generator.com/api/json/get/4ylHV_btK").readText()
        return Gson().fromJson(json, Array<Product>::class.java).toList()
    }
}