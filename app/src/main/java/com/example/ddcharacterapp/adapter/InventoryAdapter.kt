package com.example.ddcharacterapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.InventoryFragment
import com.example.ddcharacterapp.data.InventoryItemData
import com.example.ddcharacterapp.R

class InventoryAdapter(private var inventoryList: ArrayList<InventoryItemData>, var frag : InventoryFragment) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var adapter : InventoryAdapter
        init {
            // Define click listener for the ViewHolder's View
            view.findViewById<Button>(R.id.inventory_item_delete_button).setOnClickListener() {
                adapter.frag.onDeletePressed(adapterPosition)
                adapter.notifyItemRemoved(adapterPosition)
                adapter.notifyItemRangeChanged(0, adapter.inventoryList.size)
                adapter.notifyDataSetChanged()
            }
            view.findViewById<EditText>(R.id.tv_lang_name).addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0 != null) {
                        adapter.inventoryList[adapterPosition].title = p0.toString()
                        adapter.inventoryList[adapterPosition].titleSaved = true
                    }
                }

            })

            view.findViewById<EditText>(R.id.tv_description).addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0 != null) {
                        adapter.inventoryList[adapterPosition].body = p0.toString()
                        adapter.inventoryList[adapterPosition].bodySaved = true
                    }
                }

            })
        }

        fun linkAdapter(adapter : InventoryAdapter) : InventoryAdapter.ViewHolder {
            this.adapter = adapter
            return this
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryAdapter.ViewHolder {
        //create new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.inventory_item, parent,false)
        return InventoryAdapter.ViewHolder(adapterLayout).linkAdapter(this)
    }

    override fun getItemCount(): Int {
        return inventoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(inventoryList[position]){
                // set title from the list
                if(inventoryList[position].titleSaved) {
                    view.findViewById<EditText>(R.id.tv_lang_name).text = Editable.Factory.getInstance().newEditable(this.title)
                }
                else {
                    view.findViewById<EditText>(R.id.tv_lang_name).hint = this.title
                }

                // set description to the text
                // since this is inside "expandedView" its visibility will be gone initially
                // after click on the item we will make the visibility of the "expandedView" visible
                // which will also make the visibility of desc also visible
                if(inventoryList[position].bodySaved) {
                    view.findViewById<EditText>(R.id.tv_description).text = Editable.Factory.getInstance().newEditable(this.body)
                }
                else {
                    view.findViewById<EditText>(R.id.tv_description).hint = this.body
                }

                // check if boolean property "extend" is true or false
                // if it is true make the "extendedView" Visible
                view.findViewById<RelativeLayout>(R.id.expanded_view).visibility = if (this.expand) View.VISIBLE else View.GONE
                // on Click of the item take parent card view in our case
                // revert the boolean "expand"
                view.findViewById<CardView>(R.id.inventory_item_card).setOnClickListener {
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