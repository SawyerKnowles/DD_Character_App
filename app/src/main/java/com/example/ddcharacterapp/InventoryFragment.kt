package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.InventoryAdapter
import com.example.ddcharacterapp.data.InventoryItemData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InventoryFragment : Fragment() {

    var inventoryList = ArrayList<InventoryItemData>()
    lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onDetach() {

        val mainAct =  (activity as MainActivity)
        mainAct.dataManager.inventoryData.inventoryDataList = inventoryList // set inventory list for main activity DM
        Log.d("InventoryFragment", "InventoryFragment destroyed.")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity

        Log.d("InventoryFragment", "InventoryFragment created.")
        val mainAct =  (activity as MainActivity)

        mainAct.supportActionBar?.title = mainAct.characterListGlobal[mainAct.characterIndex].charName

        inventoryList = mainAct.dataManager.inventoryData.inventoryDataList // get inventory list from main activity DM

        recyclerView = act.findViewById<RecyclerView>(R.id.inventory_recycler)
        //val notesList = mutableListOf<NoteData>()
        recyclerView.adapter = InventoryAdapter(inventoryList, this)

        var i = 0

        val inventoryFAB = act.findViewById<FloatingActionButton>(R.id.inventoryFAB)
        inventoryFAB.setOnClickListener() {

            //val newNoteView = layoutInflater.inflate(R.layout.list_item, null)
            //view?.findViewById<RecyclerView>(R.id.notes_recycler)?.addView(newNoteView)
            val body = "Item Body"
            val title = "Item Title"
            //inventoryList.add(InventoryItemData(Editable.Factory.getInstance().newEditable(title), Editable.Factory.getInstance().newEditable(body), false))
            inventoryList.add(InventoryItemData(title, body, false))
            i++
            recyclerView.adapter = InventoryAdapter(inventoryList, this)
        }

    }

    fun onDeletePressed(position: Int) {
        inventoryList.removeAt(position)
        updateAdapter()
    }

    fun updateAdapter() {
        val act = (activity as AppCompatActivity)
        val recyclerView = act.findViewById<RecyclerView>(R.id.inventory_recycler)
        recyclerView.adapter = InventoryAdapter(inventoryList, this)
    }
}