package com.example.keopi

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.keopi.activities.ShowRecycledList
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

        linkViews()
        setSpinners()

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

        val beanAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            beanTypeList
        )

        val roastAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            roastLevelList
        )

        val intensityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            intensityList
        )

        val presentationAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            presentationList
        )

        val weightAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            weightList
        )

        beanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        roastAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        intensityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        presentationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        beanType.adapter = beanAdapter
        roastLevel.adapter = roastAdapter
        intensity.adapter = intensityAdapter
        presentation.adapter = presentationAdapter
        weight.adapter = weightAdapter
    }

    fun saveData() {
        validate()
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
            price.text.toString().toDoubleOrNull()?: 0.0
        )

        CoffeeStorage.coffeeList.add(data)

        Toast.makeText(this, "Enviado...", Toast.LENGTH_SHORT).show()
    }

    fun validate()
    {

    }

    fun showData() {
        val nextView = Intent(this, ShowRecycledList::class.java)
        startActivity(nextView)
    }
}