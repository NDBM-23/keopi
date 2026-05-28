package com.example.keopi.activities

import android.content.Intent
import com.example.keopi.utils.CoffeeStorage
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keopi.adapters.seeCoffeeAdapter
import com.example.keopi.MainActivity
import com.example.keopi.R

class RegistersList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show_recycled_list)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recycler_view = findViewById<RecyclerView>(R.id.see_recicler_list)
        recycler_view.layoutManager = LinearLayoutManager(this)

        val adapter = seeCoffeeAdapter(CoffeeStorage.coffeeList)
        recycler_view.adapter = adapter

        adapter.notifyDataSetChanged()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ver)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.register)
        {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
        else if (item.itemId == R.id.seeRegisters)
        {
            Toast.makeText(this, "Ya se encuentra en Ver", Toast.LENGTH_LONG).show()
        }
        else if (item.itemId == R.id.updateRegisters)
        {
            startActivity(Intent(this, UpdateRegister::class.java))
            this.finish()
        }
        else if (item.itemId == R.id.deleteRegisters) {
            startActivity(Intent(this, DeleteRegister::class.java))
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}