package com.example.keopi.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.keopi.MainActivity
import com.example.keopi.R

class SignIn : AppCompatActivity() {
    lateinit var inUsername: EditText
    lateinit var inPassword: EditText
    lateinit var btn_sign_in: Button
    lateinit var prefe: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefe = getSharedPreferences("usuarios", MODE_PRIVATE)
        val sesion = prefe.getBoolean("sesion", false)
        val rol = prefe.getString("rol", "")

        if (sesion) {
            val intent = if (rol == "Administrador") {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, RegistersList::class.java)
            }
            startActivity(intent)
            this.finish()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        linkViews()

        btn_sign_in.setOnClickListener {
            signIn()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sign_in)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun linkViews() {
        inUsername = findViewById(R.id.username)
        inPassword = findViewById(R.id.password)
        btn_sign_in = findViewById(R.id.btn_sign_in)
    }

    fun signIn() {
        val users: List<userPassw> = listOf(
            userPassw("admin", "admin"),
            userPassw("worker", "worker")
        )

        val txtUsername = inUsername.text.toString().trim()
        val txtPassword = inPassword.text.toString()

        val usuarioEncontrado =
            users.find { it.username == txtUsername && it.password == txtPassword }

        if (usuarioEncontrado != null) {
            val rolAsignado =
                if (usuarioEncontrado.username == "admin") "Administrador" else "Trabajador"

            prefe.edit().apply {
                putBoolean("sesion", true)
                putString("rol", rolAsignado)
                apply()
            }

            val intent = if (rolAsignado == "Administrador") {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, RegistersList::class.java)
            }

            Toast.makeText(this, "Bienvenido: ${usuarioEncontrado.username}", Toast.LENGTH_SHORT)
                .show()
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}

data class userPassw(
    val username: String,
    val password: String
)
