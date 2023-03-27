package com.example.ddcharacterapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*


class MainActivity : AppCompatActivity(), MenuProvider {

    val FILE_NAME = "DDCharAppData.json"

    var characterListGlobal = ArrayList<CharacterCardData>() // Global List of Character Data
    var characterIndex = 0 // Global index in characterListGlobal for currently selected character
    var dataManager = DataManager() // Global DM container - set to Character specific DM in CharacterCardAdapter

    private fun saveData() {

        val gson = Gson()
        val json = gson.toJson(characterListGlobal)

        val fileOutputStream:FileOutputStream
        try {
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE)
            fileOutputStream.write(json.toByteArray())
        } catch (e: FileNotFoundException){
            e.printStackTrace()
        }catch (e: NumberFormatException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    private fun loadData() {

        val file = this.getFileStreamPath(FILE_NAME)
        if (file == null || !file.exists()) {
            return
        }

        val gson = Gson()
        var fileInputStream: FileInputStream? = null
        fileInputStream = openFileInput(FILE_NAME)
        var inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val json = bufferedReader.use { it.readText() }
        val type = object : TypeToken<ArrayList<CharacterCardData>>() {}.type
        if(json == null) {
            return
        }
        characterListGlobal = gson.fromJson(json, type)

    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //removeMenuProvider(this@MainActivity)
            onSupportNavigateUp()
        }
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

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
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
        supportActionBar?.title = dataManager.traitsData.basicData.name
        return navigated || super.onOptionsItemSelected(menuItem)
    }

    override fun onStop() {
        Log.d("MainActivity", "onStop Called.")
        saveData()
        super.onStop()
    }
}
