package com.example.keopi.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keopi.R
import com.example.keopi.activities.SeeRegister
import com.example.keopi.utils.CoffeeDataClass

class seeCoffeeAdapter(private val list: List<CoffeeDataClass>):
RecyclerView.Adapter<seeCoffeeAdapter.ViewHolderClass>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.see_row_container, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolderClass,
        position: Int
    ) {
        val item = list[position]
        holder.brand_name.text=item.brandName
        holder.company_name.text=item.companyName

        holder.brand_name.setOnClickListener {
            val context = holder.itemView.context
            val target = Intent(context, SeeRegister::class.java)

            target.putExtra("index", position)

            context.startActivity(target)
            (context as Activity).finish()
        }
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