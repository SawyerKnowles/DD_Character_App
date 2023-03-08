package com.example.ddcharacterapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuProvider
import androidx.core.view.iterator
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.CharacterCardAdapter
import com.example.ddcharacterapp.data.NoteData
import com.example.ddcharacterapp.data.NotesData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity(), MenuProvider {

    //val actViewModel : DataViewModel by viewModels()
    //var notesList = ArrayList<NoteData>()
    //var notesData = NotesData(notesList)
    var characterListGlobal = ArrayList<CharacterCardData>() // Global List of Character Data
    var characterIndex = 0 // Global index in characterListGlobal for currently selected character
    var dataManager = DataManager() // Global DM container - set to Character specific DM in CharacterCardAdapter

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

    public fun saveData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(characterListGlobal)
        editor.putString("character list", json)
        editor.apply()
    }

    public fun loadData() {
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

        /*
        actViewModel.dataList.observe(this, Observer {
            notesData.notesDataList = it
        })

         */

        //val body = "Note Body"
        //val title = "Note Title"
        //dataManager.notesData.notesDataList.add(NoteData(Editable.Factory.getInstance().newEditable(title), Editable.Factory.getInstance().newEditable(body), false))

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
