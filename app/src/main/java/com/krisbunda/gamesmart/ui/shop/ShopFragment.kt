package com.krisbunda.gamesmart.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.krisbunda.gamesmart.R
import com.krisbunda.gamesmart.R.id.text_shop

import com.krisbunda.gamesmart.ui.shop.ShopViewModel

class ShopFragment : Fragment() {

    private lateinit var shopViewModel : ShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shopViewModel =
            ViewModelProvider(this).get(ShopViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shop, container, false)
        val textView: TextView = root.findViewById(R.id.text_shop)
        shopViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}