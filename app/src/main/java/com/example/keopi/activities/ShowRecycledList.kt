package com.example.keopi.activities

import com.example.keopi.utils.CoffeeStorage
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keopi.CoffeeAdapter
import com.example.keopi.R

class ShowRecycledList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver)

        val recycler_view = findViewById<RecyclerView>(R.id.recicler_view)
        recycler_view.layoutManager = LinearLayoutManager(this)

        val adapter = CoffeeAdapter(CoffeeStorage.coffeeList)
        recycler_view.adapter = adapter

        adapter.notifyDataSetChanged()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ver)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}