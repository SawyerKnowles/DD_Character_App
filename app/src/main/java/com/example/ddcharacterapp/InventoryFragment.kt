package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.InventoryAdapter
import com.example.ddcharacterapp.adapter.ItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InventoryFragment : Fragment() {

    val inventoryList = mutableListOf<InventoryItemData>()
    lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity
        act.supportActionBar?.title = "Character Name"

        recyclerView = act.findViewById<RecyclerView>(R.id.inventory_recycler)
        //val notesList = mutableListOf<NoteData>()
        recyclerView.adapter = InventoryAdapter(inventoryList)

        var i = 0

        val inventoryFAB = act.findViewById<FloatingActionButton>(R.id.inventoryFAB)
        inventoryFAB.setOnClickListener() {

            //val newNoteView = layoutInflater.inflate(R.layout.list_item, null)
            //view?.findViewById<RecyclerView>(R.id.notes_recycler)?.addView(newNoteView)
            val body = "Item Body"
            val title = "Item Title"
            inventoryList.add(InventoryItemData(Editable.Factory.getInstance().newEditable(title), Editable.Factory.getInstance().newEditable(body), false))
            i++
            recyclerView.adapter = InventoryAdapter(inventoryList)
        }

    }
}