package com.example.easyfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Inflating the bottom navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btm_nav)
        //Instantiating/Inflating the navigation controller class
        val navController = Navigation.findNavController(this,R.id.host_fragment)
        //Setting up the bottom navigation with the Navigation Controller
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}