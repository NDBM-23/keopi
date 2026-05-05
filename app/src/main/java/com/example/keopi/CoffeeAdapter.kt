package com.example.keopi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keopi.utils.CoffeeDataClass

class CoffeeAdapter(private val list: List<CoffeeDataClass>):
RecyclerView.Adapter<CoffeeAdapter.ViewHolderClass>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_container, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolderClass,
        position: Int
    ) {
        val item = list[position]
        holder.brand_name.text=item.brandName
        holder.company_name.text=item.companyName
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolderClass (view: View):
    RecyclerView.ViewHolder(view){
        val brand_name = view.findViewById<TextView>(R.id.brand_name)
        val company_name = view.findViewById<TextView>(R.id.company_name)
    }
}