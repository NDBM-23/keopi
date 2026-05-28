package com.example.keopi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.keopi.activities.DeleteRegister
import com.example.keopi.activities.RegistersList
import com.example.keopi.activities.UpdateRegister
import com.example.keopi.utils.CoffeeDataClass
import com.example.keopi.utils.CoffeeStorage

class MainActivity : AppCompatActivity() {
    lateinit var brandName: EditText
    lateinit var originCountry: EditText
    lateinit var companyName: EditText
    lateinit var contactPhone: EditText

    lateinit var beanType: Spinner
    lateinit var roastLevel: Spinner
    lateinit var intensity: Spinner
    lateinit var presentation: Spinner
    lateinit var weight: Spinner

    lateinit var price: EditText

    lateinit var saveButton: Button
    lateinit var showButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        linkViews()
        setSpinners()
        CoffeeStorage.coffeeList.addAll(

            listOf(

                CoffeeDataClass(
                    "Lavazza",
                    "Italia",
                    "Lavazza Group",
                    "3312345678",
                    "Arábica",
                    "Medio",
                    "Fuerte",
                    "Molido",
                    "500 g",
                    189.90
                ),

                CoffeeDataClass(
                    "Juan Valdez",
                    "Colombia",
                    "Procafecol",
                    "3323456789",
                    "Arábica",
                    "Oscuro",
                    "Medio",
                    "Grano",
                    "1 kg",
                    245.50
                ),

                CoffeeDataClass(
                    "Café de Olla",
                    "México",
                    "Café Tradicional MX",
                    "3334567890",
                    "Mezcla",
                    "Medio",
                    "Suave",
                    "Molido",
                    "250 g",
                    95.00
                ),

                CoffeeDataClass(
                    "Starbucks Pike Place",
                    "Estados Unidos",
                    "Starbucks Coffee Company",
                    "3345678901",
                    "Arábica",
                    "Medio",
                    "Fuerte",
                    "Cápsulas",
                    "12 pzas",
                    210.75
                )

            )

        )
        saveButton.setOnClickListener {
            saveData()
        }

        showButton.setOnClickListener {
            showData()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    fun linkViews() {
        brandName = findViewById(R.id.brand_name)
        originCountry = findViewById(R.id.origin_country)
        companyName = findViewById(R.id.company_name)
        contactPhone = findViewById(R.id.contact_phone)

        beanType = findViewById(R.id.bean_type)
        roastLevel = findViewById(R.id.roast_level)
        intensity = findViewById(R.id.intensity)
        presentation = findViewById(R.id.presentation)
        weight = findViewById(R.id.weight)

        price = findViewById(R.id.price)

        saveButton = findViewById(R.id.save_button)
        showButton = findViewById(R.id.show_button)
    }

    fun setSpinners() {
        val beanTypeList = resources.getStringArray(R.array.bean_type)
        val roastLevelList = resources.getStringArray(R.array.roast_level)
        val intensityList = resources.getStringArray(R.array.intensity)
        val presentationList = resources.getStringArray(R.array.presentation)
        val weightList = resources.getStringArray(R.array.weight)

        val layoutId = R.layout.register_spinner_row
        val textViewId = R.id.text

        val beanAdapter = ArrayAdapter(this, layoutId, textViewId, beanTypeList)
        val roastAdapter = ArrayAdapter(this, layoutId, textViewId, roastLevelList)
        val intensityAdapter = ArrayAdapter(this, layoutId, textViewId, intensityList)
        val presentationAdapter = ArrayAdapter(this, layoutId, textViewId, presentationList)
        val weightAdapter = ArrayAdapter(this, layoutId, textViewId, weightList)

        beanAdapter.setDropDownViewResource(layoutId)
        roastAdapter.setDropDownViewResource(layoutId)
        intensityAdapter.setDropDownViewResource(layoutId)
        presentationAdapter.setDropDownViewResource(layoutId)
        weightAdapter.setDropDownViewResource(layoutId)

        beanType.adapter = beanAdapter
        roastLevel.adapter = roastAdapter
        intensity.adapter = intensityAdapter
        presentation.adapter = presentationAdapter
        weight.adapter = weightAdapter
    }

    fun saveData() {
        if (!validate()) {
            return
        }
        val data = CoffeeDataClass(
            brandName.text.toString(),
            originCountry.text.toString(),
            companyName.text.toString(),
            contactPhone.text.toString(),
            beanType.selectedItem.toString(),
            roastLevel.selectedItem.toString(),
            intensity.selectedItem.toString(),
            presentation.selectedItem.toString(),
            weight.selectedItem.toString(),
            price.text.toString().toDoubleOrNull() ?: 0.0
        )

        CoffeeStorage.coffeeList.add(data)

        Toast.makeText(this, "Guardado...", Toast.LENGTH_SHORT).show()
    }

    fun validate(): Boolean {
        if (brandName.text.isNullOrBlank()) {
            Toast.makeText(this, "Ingrese la marca", Toast.LENGTH_SHORT).show()
            brandName.requestFocus()
            return false
        }

        if (originCountry.text.isNullOrBlank()) {
            Toast.makeText(this, "Ingrese el país", Toast.LENGTH_SHORT).show()
            originCountry.requestFocus()
            return false
        }

        if (companyName.text.isNullOrBlank()) {
            Toast.makeText(this, "Ingrese la empresa", Toast.LENGTH_SHORT).show()
            companyName.requestFocus()
            return false
        }

        if (contactPhone.text.isNullOrBlank()) {
            Toast.makeText(this, "Ingrese el teléfono", Toast.LENGTH_SHORT).show()
            contactPhone.requestFocus()
            return false
        }

        if (price.text.isNullOrBlank()) {
            Toast.makeText(this, "Ingrese el precio", Toast.LENGTH_SHORT).show()
            price.requestFocus()
            return false
        }

        if (beanType.selectedItemPosition == 0) {
            Toast.makeText(this, "Selecciona un tipo de grano", Toast.LENGTH_SHORT).show()
            return false
        }

        if (roastLevel.selectedItemPosition == 0) {
            Toast.makeText(this, "Selecciona el nivel de tueste", Toast.LENGTH_SHORT).show()
            return false
        }

        if (intensity.selectedItemPosition == 0) {
            Toast.makeText(this, "Selecciona la intensidad", Toast.LENGTH_SHORT).show()
            return false
        }

        if (presentation.selectedItemPosition == 0) {
            Toast.makeText(this, "Selecciona la presentación", Toast.LENGTH_SHORT).show()
            return false
        }

        if (weight.selectedItemPosition == 0) {
            Toast.makeText(this, "Selecciona el peso", Toast.LENGTH_SHORT).show()
            return false
        }

        // Si llegó hasta aquí, todo está bien
        return true
    }

    fun showData() {
        val nextView = Intent(this, RegistersList::class.java)
        startActivity(nextView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.register) {
            Toast.makeText(this, "Ya se encuentra en Registro", Toast.LENGTH_LONG).show()
        } else if (item.itemId == R.id.seeRegisters) {
            startActivity(Intent(this, RegistersList::class.java))
            this.finish()
        } else if (item.itemId == R.id.updateRegisters) {
            startActivity(Intent(this, UpdateRegister::class.java))
            this.finish()
        } else if (item.itemId == R.id.deleteRegisters) {
            startActivity(Intent(this, DeleteRegister::class.java))
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}