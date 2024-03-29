package com.example.hezellogin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hezellogin.helper.DbHelper
import com.example.hezellogin.model.Details

class MainActivity : AppCompatActivity() {
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var dbHandler: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        dbHandler = DbHelper(this, null)

        buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        buttonLogin.setOnClickListener {
            login(editTextEmail.text.toString(), editTextPassword.text.toString())
        }

    }

    //verify email
    private fun verifyEmail(email: String): Boolean {
        return email.contains("@") && email.endsWith("hezel.andahwa")
    }

    //verify password
    private fun verifyPassword(pass: String): Boolean {
        return pass.length >= 8
    }

    //login
    private fun login(email: String, pass: String) {
        if (TextUtils.isEmpty(email)) {
            editTextEmail.requestFocus()
            editTextEmail.setError("Cannot be empty")
        } else if (TextUtils.isEmpty(pass)) {
            editTextPassword.requestFocus()
            editTextPassword.setError("Cannot be empty")
        } else if (!verifyEmail(email)) {
            editTextEmail.requestFocus()
            editTextEmail.setError("Invalid email")
        } else if (!verifyPassword(pass)) {
            editTextPassword.requestFocus()
            editTextPassword.setError("Invalid password")
        } else {
            val user = Details(email,pass)

            val cursor = dbHandler.readData(user)
            if(cursor.count>=1){
                Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,TaskActivity::class.java))
                finish()
            }
            cursor.close()
        }
    }

}
