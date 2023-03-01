package com.example.ddcharacterapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.AbilityData
import com.example.ddcharacterapp.R

class AbilityAdapter(private var abilityList: List<AbilityData>) : RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var adapter : AbilityAdapter
        init {
            view.findViewById<EditText>(R.id.tv_lang_name).addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.d("beforeTextChanged", "Before Changed")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.d("onTextChanged", "On Changed")
                }

                override fun afterTextChanged(p0: Editable?) {
                    Log.d("afterTextChanged", "After Changed")
                    if (p0 != null) {
                        adapter.abilityList[adapterPosition].title = p0
                    }
                }

            })
        }

        public fun linkAdapter(adapter : AbilityAdapter) : AbilityAdapter.ViewHolder {
            this.adapter = adapter
            return this
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityAdapter.ViewHolder {
        //create new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.ability_item, parent,false)
        return AbilityAdapter.ViewHolder(adapterLayout).linkAdapter(this)
    }

    override fun getItemCount(): Int {
        return abilityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(abilityList[position]){
                // set name of the language from the list
                view.findViewById<EditText>(R.id.tv_lang_name).hint = this.title
                // set description to the text
                // since this is inside "expandedView" its visibility will be gone initially
                // after click on the item we will make the visibility of the "expandedView" visible
                // which will also make the visibility of desc also visible
                view.findViewById<EditText>(R.id.tv_description).hint = this.body
                // check if boolean property "extend" is true or false
                // if it is true make the "extendedView" Visible
                view.findViewById<RelativeLayout>(R.id.expanded_view).visibility = if (this.expand) View.VISIBLE else View.GONE
                // on Click of the item take parent card view in our case
                // revert the boolean "expand"
                view.findViewById<CardView>(R.id.ability_item_card).setOnClickListener {
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