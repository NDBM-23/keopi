package com.example.keopi.utils

data class CoffeeDataClass(
    val brandName: String,
    val originCountry: String,
    val companyName: String,
    val contactPhone: String,
    val beanType: String,
    val roastLevel: String,
    val intensity: String,
    val presentation: String,
    val weight: String,
    val price: String,
    var delete: Boolean = false
)