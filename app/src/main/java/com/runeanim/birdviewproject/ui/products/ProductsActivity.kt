package com.runeanim.birdviewproject.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.runeanim.birdviewproject.R

class ProductsActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.products_activity)

        navController = Navigation.findNavController(this, R.id.product_nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }
}
