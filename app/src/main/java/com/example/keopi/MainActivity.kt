package com.example.keopi

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val keopiList: MutableList<Keopi> = mutableListOf()
    lateinit var nombre_marca: EditText
    lateinit var pais_origen: EditText
    lateinit var nombre_empresa: EditText
    lateinit var telefono_contacto: EditText
    lateinit var tipo_grano: Spinner
    lateinit var nivel_tostado: Spinner
    lateinit var intensidad: Spinner
    lateinit var presentacion: Spinner
    lateinit var peso: Spinner
    lateinit var precio: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        linkViews()
        setSpinners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun linkViews()
    {
        nombre_marca = findViewById(R.id.nombre_marca)
        pais_origen = findViewById(R.id.pais_origen)
        nombre_empresa = findViewById(R.id.nombre_empresa)
        telefono_contacto = findViewById(R.id.telefono_contacto)
        tipo_grano = findViewById(R.id.tipo_grano)
        nivel_tostado = findViewById(R.id.nivel_tostado)
        intensidad = findViewById(R.id.intensidad)
        presentacion = findViewById(R.id.presentacion)
        peso = findViewById(R.id.peso)
        precio = findViewById(R.id.precio)
    }

    fun setSpinners(){
        val tipo_grano_list = resources.getStringArray(R.array.tipo_grano)
        val nivel_tostado_list = resources.getStringArray(R.array.nivel_tostado)
        val intensidad_list = resources.getStringArray(R.array.intensidad)
        val presentacion_list = resources.getStringArray(R.array.presentacion)
        val peso_list = resources.getStringArray(R.array.peso)

        val adapterTipo = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipo_grano_list)
        val adapterTostado = ArrayAdapter(this, android.R.layout.simple_spinner_item, nivel_tostado_list)
        val adapterIntensidad = ArrayAdapter(this, android.R.layout.simple_spinner_item, intensidad_list)
        val adapterPresentacion = ArrayAdapter(this, android.R.layout.simple_spinner_item, presentacion_list)
        val adapterPeso = ArrayAdapter(this, android.R.layout.simple_spinner_item, peso_list)

        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterTostado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterIntensidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterPresentacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterPeso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        tipo_grano.adapter = adapterTipo
        nivel_tostado.adapter = adapterTostado
        intensidad.adapter = adapterIntensidad
        presentacion.adapter = adapterPresentacion
        peso.adapter = adapterPeso
    }
}