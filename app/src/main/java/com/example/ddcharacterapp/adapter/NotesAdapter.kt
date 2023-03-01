package com.example.ddcharacterapp.adapter

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.MainActivity
import com.example.ddcharacterapp.NoteData
import com.example.ddcharacterapp.NotesFragment
import com.example.ddcharacterapp.R

class NotesAdapter(private var notesList: ArrayList<NoteData>, var frag : NotesFragment) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private var itemCard : CardView = view.findViewById<CardView>(R.id.notes_item_card)
        private lateinit var adapter : NotesAdapter
        init {
            // Define click listener for the ViewHolder's View
            view.findViewById<Button>(R.id.notes_item_delete_button).setOnClickListener() {
                //adapter.notesList.removeAt(adapterPosition)
                adapter.frag.onDeletePressed(adapterPosition)
                adapter.notifyItemRemoved(adapterPosition)
                adapter.notifyItemRangeChanged(0, adapter.notesList.size)
                adapter.notifyDataSetChanged()
            }

            view.findViewById<Button>(R.id.notes_item_save_button).setOnClickListener {
                var title = view.findViewById<EditText>(R.id.tv_lang_name).text.toString()
                var body = view.findViewById<EditText>(R.id.tv_description).text.toString()
                adapter.frag.onSavePressed(adapterPosition, title, body)
                adapter.notifyItemChanged(adapterPosition)
                adapter.notifyDataSetChanged()

            }
        }

        public fun linkAdapter(adapter : NotesAdapter) : ViewHolder {
            this.adapter = adapter
            return this
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        //create new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent,false)
        //return NotesAdapter.ViewHolder(adapterLayout)
        return NotesAdapter.ViewHolder(adapterLayout).linkAdapter(this)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return notesList.size
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(notesList[position]){
                // set name of the language from the list

                if(notesList[position].saved) {
                    view.findViewById<EditText>(R.id.tv_lang_name).text = this.title
                }
                else {
                    view.findViewById<EditText>(R.id.tv_lang_name).hint = this.title
                }

                //view.findViewById<EditText>(R.id.tv_lang_name).hint = this.title

                // set description to the text
                // since this is inside "expandedView" its visibility will be gone initially
                // after click on the item we will make the visibility of the "expandedView" visible
                // which will also make the visibility of desc also visible

                if(notesList[position].saved) {
                    view.findViewById<EditText>(R.id.tv_description).text = this.body
                }
                else {
                    view.findViewById<EditText>(R.id.tv_description).hint = this.body
                }

                //view.findViewById<EditText>(R.id.tv_description).hint = this.body

                // check if boolean property "extend" is true or false
                // if it is true make the "extendedView" Visible
                view.findViewById<RelativeLayout>(R.id.expanded_view).visibility = if (this.expand) View.VISIBLE else View.GONE
                // on Click of the item take parent card view in our case
                // revert the boolean "expand"
                view.findViewById<CardView>(R.id.notes_item_card).setOnClickListener {
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}