package com.example.hezellogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val editTextEmail=findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword=findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin=findViewById<Button>(R.id.buttonLogin)
        val buttonRegister=findViewById<Button>(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }
}
