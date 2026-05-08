package com.example.keopi

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.keopi.utils.CoffeeDataClass
import com.example.keopi.utils.CoffeeStorage
import java.util.jar.Manifest

class Card : AppCompatActivity() {
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
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tarjeta)

        linkViews()

        val pos: Int

        pos = intent.getIntExtra("index", -1)

        val i = CoffeeStorage.coffeeList[pos]
        item = i

        setTexts()

        button.setOnClickListener {
            call()
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
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), REQUEST_CALL)
        }
        else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:"+contactPhone.text.toString())
            startActivity(intent)
        }
    }
}