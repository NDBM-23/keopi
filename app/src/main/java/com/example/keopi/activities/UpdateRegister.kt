package com.example.keopi.activities

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
import com.example.keopi.MainActivity
import com.example.keopi.R
import com.example.keopi.utils.CoffeeDataClass
import com.example.keopi.utils.CoffeeStorage

class UpdateRegister : AppCompatActivity() {

    lateinit var edtBrandName: EditText
    lateinit var edtPrice: EditText
    lateinit var edtCompanyName: EditText
    lateinit var edtOriginCountry: EditText
    lateinit var edtContactPhone: EditText

    lateinit var spnBeanType: Spinner
    lateinit var spnRoastLevel: Spinner
    lateinit var spnIntensity: Spinner
    lateinit var spnPresentation: Spinner
    lateinit var spnWeight: Spinner

    lateinit var back: Button
    lateinit var save: Button
    lateinit var next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (CoffeeStorage.coffeeList.isEmpty()) {
            Toast.makeText(this, "No hay nada registrado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_update_card)
        var pos: Int = 0

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        linkViews()
        setSpinners()
        setHints(CoffeeStorage.coffeeList[pos])

        back.setOnClickListener {
            if (pos == 0) {
                pos = CoffeeStorage.coffeeList.size - 1
            } else {
                pos--
            }
            setHints(CoffeeStorage.coffeeList[pos])
        }
        save.setOnClickListener {
            if (!validate()) return@setOnClickListener

            val data = CoffeeDataClass(
                edtBrandName.text.toString(),
                edtOriginCountry.text.toString(),
                edtCompanyName.text.toString(),
                edtContactPhone.text.toString(),
                spnBeanType.selectedItem.toString(),
                spnRoastLevel.selectedItem.toString(),
                spnIntensity.selectedItem.toString(),
                spnPresentation.selectedItem.toString(),
                spnWeight.selectedItem.toString(),
                edtPrice.text.toString()
            )

            CoffeeStorage.coffeeList[pos] = data

            Toast.makeText(this, "Actualizado...", Toast.LENGTH_SHORT).show()
        }
        next.setOnClickListener {
            if (pos == CoffeeStorage.coffeeList.size - 1) {
                pos = 0
            } else {
                pos++
            }
            setHints(CoffeeStorage.coffeeList[pos])
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.update)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun linkViews() {
        edtBrandName = findViewById(R.id.edtBrandName)
        edtPrice = findViewById(R.id.edtPrice)
        edtCompanyName = findViewById(R.id.edtCompanyName)
        edtOriginCountry = findViewById(R.id.edtOriginCountry)
        edtContactPhone = findViewById(R.id.edtContactPhone)

        spnBeanType = findViewById(R.id.spnBeanType)
        spnRoastLevel = findViewById(R.id.spnRoastLevel)
        spnIntensity = findViewById(R.id.spnIntensity)
        spnPresentation = findViewById(R.id.spnPresentation)
        spnWeight = findViewById(R.id.spnWeight)

        back = findViewById(R.id.back)
        save = findViewById(R.id.save)
        next = findViewById(R.id.next)
    }

    fun setSpinners() {
        setSpinner(
            spnBeanType,
            R.array.bean_type
        )

        setSpinner(
            spnRoastLevel,
            R.array.roast_level
        )

        setSpinner(
            spnIntensity,
            R.array.intensity
        )

        setSpinner(
            spnPresentation,
            R.array.presentation
        )

        setSpinner(
            spnWeight,
            R.array.weight
        )
    }

    fun setSpinner(
        spinner: Spinner,
        array: Int
    ) {

        val adapter = ArrayAdapter(
            this,
            R.layout.update_spinner_row,
            R.id.text,
            resources.getStringArray(array)
        )

        adapter.setDropDownViewResource(
            R.layout.update_spinner_row
        )

        spinner.adapter = adapter
    }

    fun setSpinnerSelection(
        spinner: Spinner,
        value: String
    ) {
        val adapter = spinner.adapter as ArrayAdapter<String>

        val position = adapter.getPosition(value)

        if (position >= 0) {
            spinner.setSelection(position)
        }
    }

    fun setHints(item: CoffeeDataClass) {
        edtBrandName.setText(item.brandName)
        edtPrice.setText(item.price)
        edtCompanyName.setText(item.companyName)
        edtOriginCountry.setText(item.originCountry)
        edtContactPhone.setText(item.contactPhone)

        setSpinnerSelection(
            spnBeanType, item.beanType
        )
        setSpinnerSelection(
            spnRoastLevel, item.roastLevel
        )
        setSpinnerSelection(
            spnIntensity, item.intensity
        )
        setSpinnerSelection(
            spnPresentation, item.presentation
        )
        setSpinnerSelection(
            spnWeight, item.weight
        )
    }

    fun validate(): Boolean {

        if (edtBrandName.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Ingrese la marca", Toast.LENGTH_SHORT).show()
            edtBrandName.requestFocus()
            return false
        }

        if (edtCompanyName.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Ingrese la empresa", Toast.LENGTH_SHORT).show()
            edtCompanyName.requestFocus()
            return false
        }

        if (edtOriginCountry.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Ingrese el país de origen", Toast.LENGTH_SHORT).show()
            edtOriginCountry.requestFocus()
            return false
        }

        val phone = edtContactPhone.text.toString().trim()

        if (phone.isEmpty()) {
            Toast.makeText(this, "Ingrese el teléfono", Toast.LENGTH_SHORT).show()
            edtContactPhone.requestFocus()
            return false
        }

        if (phone.length != 10 || !phone.all { it.isDigit() }) {
            Toast.makeText(this, "Teléfono de 10 dígitos", Toast.LENGTH_SHORT).show()
            edtContactPhone.requestFocus()
            return false
        }

        if (spnBeanType.selectedItemPosition == 0) {
            Toast.makeText(this, "Seleccione un tipo de grano", Toast.LENGTH_SHORT).show()
            return false
        }

        if (spnRoastLevel.selectedItemPosition == 0) {
            Toast.makeText(this, "Seleccione el nivel de tostado", Toast.LENGTH_SHORT).show()
            return false
        }

        if (spnIntensity.selectedItemPosition == 0) {
            Toast.makeText(this, "Seleccione la intensidad", Toast.LENGTH_SHORT).show()
            return false
        }

        if (spnPresentation.selectedItemPosition == 0) {
            Toast.makeText(this, "Seleccione la presentación", Toast.LENGTH_SHORT).show()
            return false
        }

        if (spnWeight.selectedItemPosition == 0) {
            Toast.makeText(this, "Seleccione el peso", Toast.LENGTH_SHORT).show()
            return false
        }

        val priceValue = edtPrice.text.toString().toDoubleOrNull()

        if (priceValue == null) {
            Toast.makeText(this, "Ingrese un precio válido", Toast.LENGTH_SHORT).show()
            edtPrice.requestFocus()
            return false
        }

        if (priceValue <= 0) {
            Toast.makeText(this, "El precio debe ser mayor a cero", Toast.LENGTH_SHORT).show()
            edtPrice.requestFocus()
            return false
        }

        return true
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
            Toast.makeText(this, "Ya se encuentra en Actualizar", Toast.LENGTH_SHORT).show()
        } else if (item.itemId == R.id.deleteRegisters) {
            startActivity(Intent(this, DeleteRegister::class.java))
            this.finish()
        } else if (item.itemId == R.id.creator) {
            startActivity(Intent(this, Creator::class.java))
            this.finish()
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