package com.example.ddcharacterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuProvider
import androidx.core.view.iterator
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.CharacterCardAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), MenuProvider {

    /* On Input Listener Functions - Deprecated for Fragment implementation
    private fun addNewCharacterToList(newName: String, newClass: String, newLevel: String) {
        Log.d("Check2", "Activity addNewCharacterToList Called")
        val charLayout = findViewById<LinearLayout>(R.id.charLayout)
        val newCharView = layoutInflater.inflate(R.layout.list_item_character, null)

        val menuName = newCharView.findViewById<TextView>(R.id.mName)
        val menuClass = newCharView.findViewById<TextView>(R.id.mMenuClass)
        val menuLevel = newCharView.findViewById<TextView>(R.id.mMenuLevel)
        menuName.text = newName
        menuClass.text = newClass
        menuLevel.text = newLevel

        val characterCard = newCharView.findViewById<CardView>(R.id.characterCard)
        characterCard.setOnClickListener() {
            Log.d("CharacterName", "Character Name: " + menuName.text.toString())
            Log.d("CharacterClass", "Character Class: " + menuClass.text.toString())
            Log.d("CharacterLevel", "Character Level: " + menuLevel.text.toString())
            addMenuProvider(this)
            Navigation.findNavController(it).navigate(R.id.destination_stats)
        }

        charLayout.addView(newCharView)
    }

    override fun sendNewCharacterInfo(newName: String, newClass: String, newLevel: String) {
        Log.d("Check", "Activity sendNewCharacterInfo Called")
        //addNewCharacterToList(newName, newClass, newLevel)
    }

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBar(navController)

        /* Add Character without using Navigation
        val addCharButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        /* Get Character Info Using Activity
        //Launch InputCharacterInfo activity for results
        var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                //doSomeOperations()
                val nameStr = data?.getStringExtra("name")
                val classStr = data?.getStringExtra("class")
                val levelStr = data?.getStringExtra("level")

                val newCharView = layoutInflater.inflate(R.layout.list_item_character, null)

                val menuName = newCharView.findViewById<TextView>(R.id.mName)
                val menuClass = newCharView.findViewById<TextView>(R.id.mMenuClass)
                val menuLevel = newCharView.findViewById<TextView>(R.id.mMenuLevel)
                menuName.text = nameStr
                menuClass.text = classStr
                menuLevel.text = levelStr

                val characterCard = newCharView.findViewById<CardView>(R.id.characterCard)
                characterCard.setOnClickListener() {
                    Log.d("asdasd", "asdasd")
                }

                charLayout.addView(newCharView)
            }
        }*/

        addCharButton.setOnClickListener() {
            var dialog = NewCharacterDialogFragment()
            dialog.show(supportFragmentManager, "newCharacterDialog")

            /* Start Character Info View Using Activity
            val charInfoPop = Intent(this, InputCharacterInfo::class.java)
            resultLauncher.launch(charInfoPop)
            //startActivity(charInfoPop)*/
        }
         */
    }

    private fun setupActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        removeMenuProvider(this)
        Log.d("NavUp", "Should be removed")
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
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater.inflate(R.menu.menu_navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navigated = NavigationUI.onNavDestinationSelected(item, navController)
        return navigated || super.onOptionsItemSelected(item)

        /*
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        when(item.itemId) {
            R.id.destination_stats -> navController.navigate(R.id.destination_stats)
        }
        return super.onOptionsItemSelected(item)
         */


    }
 */
}
