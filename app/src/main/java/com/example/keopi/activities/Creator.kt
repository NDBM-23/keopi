package com.example.keopi.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.keopi.MainActivity
import com.example.keopi.R

class Creator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_creator)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.creator)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val prefe = getSharedPreferences("usuarios", MODE_PRIVATE)
        val rol = prefe.getString("rol", "")

        if (rol == "Trabajador") {
            menu?.findItem(R.id.register)?.isVisible = false
            menu?.findItem(R.id.updateRegisters)?.isVisible = false
            menu?.findItem(R.id.deleteRegisters)?.isVisible = false
        }

        return true
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
            startActivity(Intent(this, DeleteRegister::class.java))
            this.finish()
        } else if (item.itemId == R.id.creator) {
            Toast.makeText(this, "Ya se encuentra en Creador", Toast.LENGTH_SHORT).show()
        } else if (item.itemId == R.id.contact) {
            startActivity(Intent(this, Contact::class.java))
            this.finish()
        } else if (item.itemId == R.id.sign_out) {
            val prefe = getSharedPreferences("usuarios", MODE_PRIVATE)

            prefe.edit().apply {
                putBoolean("sesion", false)
                remove("rol")
                apply()
            }

            val intent = Intent(this, SignIn::class.java)

            startActivity(intent)
            finish()

            return true
        }
        return super.onOptionsItemSelected(item)
    }
}