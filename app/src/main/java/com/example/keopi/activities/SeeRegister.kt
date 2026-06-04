package com.example.keopi.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.keopi.MainActivity
import com.example.keopi.R
import com.example.keopi.utils.CoffeeDataClass
import com.example.keopi.utils.CoffeeStorage

class SeeRegister : AppCompatActivity() {
    lateinit var item: CoffeeDataClass
    private val REQUEST_CALL = 1
    lateinit var brandName: TextView
    lateinit var originCountry: TextView
    lateinit var companyName: TextView
    lateinit var contactPhone: TextView

    lateinit var beanType: TextView
    lateinit var roastLevel: TextView
    lateinit var intensity: TextView
    lateinit var presentation: TextView
    lateinit var weight: TextView

    lateinit var price: TextView

    lateinit var button: Button
    lateinit var btn_back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (CoffeeStorage.coffeeList.isEmpty()) {
            Toast.makeText(this, "No hay nada registrado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, RegistersList::class.java))
            finish()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_see_card)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        linkViews()

        val pos: Int = intent.getIntExtra("index", -1)

        if (pos != -1 && pos < CoffeeStorage.coffeeList.size) {
            val i = CoffeeStorage.coffeeList[pos]
            item = i

            setTexts()
        } else {
            Toast.makeText(this, "Elija Cafe Valido", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, RegistersList::class.java))
            finish()
        }

        button.setOnClickListener {
            call()
        }
        btn_back.setOnClickListener {
            startActivity(Intent(this, RegistersList::class.java))
            this.finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun linkViews(){
        brandName = findViewById(R.id.txtBrand)
        originCountry = findViewById(R.id.txtCountry)
        companyName = findViewById(R.id.txtCompany)
        contactPhone = findViewById(R.id.txtPhone)
        beanType = findViewById(R.id.txtBean)
        roastLevel = findViewById(R.id.txtRoast)
        intensity = findViewById(R.id.txtIntensity)
        presentation = findViewById(R.id.txtPresentation)
        weight = findViewById(R.id.txtWeight)
        price = findViewById(R.id.txtPrice)
        button = findViewById(R.id.button)
        btn_back = findViewById(R.id.back)
    }

    fun setTexts(){
        brandName.text = item.brandName
        originCountry.text = item.originCountry
        companyName.text = item.companyName
        contactPhone.text = item.contactPhone
        beanType.text = item.beanType
        roastLevel.text = item.roastLevel
        intensity.text = item.intensity
        presentation.text = item.presentation
        weight.text = item.weight
        price.text = item.price.toString()
    }

    fun call(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
        }
        else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:"+contactPhone.text.toString())
            startActivity(intent)
        }
    }
}