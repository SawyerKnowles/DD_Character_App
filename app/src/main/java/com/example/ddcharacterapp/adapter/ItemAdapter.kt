package com.example.ddcharacterapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.data.NoteData
import com.example.ddcharacterapp.R
import com.example.ddcharacterapp.RecyclerViewInterface

class ItemAdapter(private val notesList: List<NoteData>, private val context: Context, private val recyclerViewInterface: RecyclerViewInterface) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View, recyclerViewInterface: RecyclerViewInterface) : RecyclerView.ViewHolder(view) {
        val itemTextView: TextView = view.findViewById(R.id.item_title)
        // Item on click listener uses RecyclerViewInterface for NotesFragment to implement
        init {
            view.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION) {
                    recyclerViewInterface.onItemClick(pos)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //create new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return ItemViewHolder(adapterLayout, recyclerViewInterface)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemTextView.text = notesList[position].title
    }

}