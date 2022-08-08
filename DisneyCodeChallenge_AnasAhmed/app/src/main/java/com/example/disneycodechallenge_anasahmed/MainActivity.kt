package com.example.disneycodechallenge_anasahmed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var selectButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectButton = findViewById(R.id.main_button)
        selectButton.setOnClickListener{
            startActivity(Intent(this, SelectActivity::class.java))
        }
    }
}