package com.example.ddcharacterapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.InventoryFragment
import com.example.ddcharacterapp.R
import com.example.ddcharacterapp.SpellData
import com.example.ddcharacterapp.SpellsFragment

class SpellAdapter(private var spellList: ArrayList<SpellData>, var frag : SpellsFragment) : RecyclerView.Adapter<SpellAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var adapter: SpellAdapter

        init {
            // Define click listener for the ViewHolder's View
            view.findViewById<Button>(R.id.spells_item_delete_button).setOnClickListener() {
                //adapter.notesList.removeAt(adapterPosition)
                adapter.spellList.removeAt(adapterPosition)
                adapter.frag.updateAdapters()
                adapter.notifyItemRemoved(adapterPosition)
                adapter.notifyItemRangeChanged(0, adapter.spellList.size)
                adapter.notifyDataSetChanged()
            }

            view.findViewById<EditText>(R.id.tv_lang_name).addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.d("beforeTextChanged", "Before Changed")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.d("onTextChanged", "On Changed")
                }

                override fun afterTextChanged(p0: Editable?) {
                    Log.d("afterTextChanged", "After Changed")
                    if (p0 != null) {
                        adapter.spellList[adapterPosition].title = p0
                        adapter.spellList[adapterPosition].titleSaved = true
                    }
                }

            })

            view.findViewById<EditText>(R.id.tv_description).addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.d("beforeTextChanged", "Before Changed")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.d("onTextChanged", "On Changed")
                }

                override fun afterTextChanged(p0: Editable?) {
                    Log.d("afterTextChanged", "After Changed")
                    if (p0 != null) {
                        adapter.spellList[adapterPosition].body = p0
                        adapter.spellList[adapterPosition].bodySaved = true
                    }
                }

            })

            view.findViewById<CheckBox>(R.id.spell_prep_checkbox).setOnCheckedChangeListener(object :
                CompoundButton.OnCheckedChangeListener {

                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    adapter.spellList[adapterPosition].prepared = p1
                }

            })
        }

        public fun linkAdapter(adapter : SpellAdapter) : SpellAdapter.ViewHolder {
            this.adapter = adapter
            return this
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellAdapter.ViewHolder {
        //create new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.spells_item, parent,false)
        return SpellAdapter.ViewHolder(adapterLayout).linkAdapter(this)
    }

    override fun getItemCount(): Int {
        return spellList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(spellList[position]){
                // set name of the language from the list

                if(spellList[position].titleSaved) {
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

                if(spellList[position].bodySaved) {
                    view.findViewById<EditText>(R.id.tv_description).text = this.body
                }
                else {
                    view.findViewById<EditText>(R.id.tv_description).hint = this.body
                }

                view.findViewById<CheckBox>(R.id.spell_prep_checkbox).isChecked = spellList[position].prepared
                //view.findViewById<EditText>(R.id.tv_description).hint = this.body

                // check if boolean property "extend" is true or false
                // if it is true make the "extendedView" Visible
                view.findViewById<RelativeLayout>(R.id.expanded_view).visibility = if (this.expand) View.VISIBLE else View.GONE
                // on Click of the item take parent card view in our case
                // revert the boolean "expand"
                view.findViewById<CardView>(R.id.spells_item_card).setOnClickListener {
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