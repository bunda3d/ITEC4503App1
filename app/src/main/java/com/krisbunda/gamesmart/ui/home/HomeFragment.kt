package com.krisbunda.gamesmart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.krisbunda.gamesmart.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val btnShopLink: Button = root.findViewById(R.id.card_home_cta_link)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //text set in ViewModel class
            btnShopLink.text = it
            btnShopLink.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.navcat_home, null
                )
            )
        })

        return root
    }
}