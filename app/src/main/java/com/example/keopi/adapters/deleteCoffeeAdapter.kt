package com.example.keopi.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keopi.R
import com.example.keopi.activities.SeeRegister
import com.example.keopi.utils.CoffeeDataClass

class deleteCoffeeAdapter(private val list: List<CoffeeDataClass>) :
    RecyclerView.Adapter<deleteCoffeeAdapter.ViewHolderClass>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): deleteCoffeeAdapter.ViewHolderClass {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.delete_row_container, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(
        holder: deleteCoffeeAdapter.ViewHolderClass,
        position: Int
    ) {
        val item = list[position]

        holder.brand.text = item.brandName
        holder.weight.text = item.weight
        holder.presentation.text = item.presentation
        holder.company.text = item.companyName

        holder.checkbox.setOnCheckedChangeListener(null)

        holder.checkbox.isChecked = item.delete

        holder.checkbox.setOnCheckedChangeListener { button, bool ->
            item.delete = bool
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolderClass(view: View) :
        RecyclerView.ViewHolder(view) {
        val checkbox = view.findViewById<CheckBox>(R.id.checkbox)
        val brand = view.findViewById<TextView>(R.id.brand)
        val weight = view.findViewById<TextView>(R.id.weight)
        val presentation = view.findViewById<TextView>(R.id.presentation)
        val company = view.findViewById<TextView>(R.id.company)
    }
}