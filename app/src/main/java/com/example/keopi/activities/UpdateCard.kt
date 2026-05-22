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

class UpdateCard : AppCompatActivity() {

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
                edtPrice.text.toString().toDoubleOrNull()?: 0.0
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
            android.R.layout.simple_spinner_item,
            resources.getStringArray(array)
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
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
        edtPrice.setText(item.price.toString())
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.register) {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        } else if (item.itemId == R.id.seeRegisters) {
            startActivity(Intent(this, ShowRecycledList::class.java))
            this.finish()
        } else if (item.itemId == R.id.updateRegisters) {
            Toast.makeText(this, "Ya se encuentra en Actualizar", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}