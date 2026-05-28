package com.example.keopi.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keopi.MainActivity
import com.example.keopi.R
import com.example.keopi.adapters.deleteCoffeeAdapter
import com.example.keopi.utils.CoffeeStorage

class DeleteRegister : AppCompatActivity() {
    lateinit var recycler_view: RecyclerView
    lateinit var delete_btn: Button

    val adapter = deleteCoffeeAdapter(CoffeeStorage.coffeeList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete_register)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        linkViews()

        recycler_view.layoutManager = LinearLayoutManager(this)


        recycler_view.adapter = adapter

        adapter.notifyDataSetChanged()

        delete_btn.setOnClickListener {
            deleteSelected()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.delete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun linkViews() {
        recycler_view = findViewById(R.id.delete_recycler_list)
        delete_btn = findViewById(R.id.delete_registers)
    }

    fun deleteSelected() {
        val it = CoffeeStorage.coffeeList.iterator()

        while (it.hasNext()) {
            val cafe = it.next()
            if (cafe.delete) {
                it.remove()
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.register) {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        } else if (item.itemId == R.id.seeRegisters) {
            startActivity(Intent(this, RegistersList::class.java))
            this.finish()
        } else if (item.itemId == R.id.updateRegisters) {
            startActivity(Intent(this, UpdateRegister::class.java))
            this.finish()
        } else if (item.itemId == R.id.deleteRegisters) {

            Toast.makeText(this, "Ya se encuentra en Borrar", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}