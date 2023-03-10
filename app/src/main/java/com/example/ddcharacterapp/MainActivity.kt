package com.example.ddcharacterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity(), MenuProvider {

    var characterListGlobal = ArrayList<CharacterCardData>() // Global List of Character Data
    var characterIndex = 0 // Global index in characterListGlobal for currently selected character
    var dataManager = DataManager() // Global DM container - set to Character specific DM in CharacterCardAdapter

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(characterListGlobal)
        editor.putString("character list", json)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("character list", null)
        val type = object : TypeToken<ArrayList<CharacterCardData>>() {}.type
        if(json == null) {
            return
        }
        characterListGlobal = gson.fromJson(json, type)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_main)

        loadData()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBar(navController)
    }

    private fun setupActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        removeMenuProvider(this)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_navigation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navigated = NavigationUI.onNavDestinationSelected(menuItem, navController)
        return navigated || super.onOptionsItemSelected(menuItem)
    }

    override fun onStop() {
        Log.d("MainActivity", "onStop Called.")
        saveData()
        super.onStop()
    }

}
