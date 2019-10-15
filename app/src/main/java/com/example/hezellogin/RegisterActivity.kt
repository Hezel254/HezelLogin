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

class RegisterActivity : AppCompatActivity() {

    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextCPassword: EditText
    lateinit var dbHandler:DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        editTextCPassword = findViewById<EditText>(R.id.editTextCPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        dbHandler = DbHelper(this, null)


        buttonLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        buttonRegister.setOnClickListener {
            register(editTextEmail.text.toString(),editTextPassword.text.toString(),editTextCPassword.text.toString())
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

    //passwords match
    private fun passMatch(pass: String, cPass: String): Boolean {
        return pass.equals(cPass)
    }

    //register
    private fun register(email: String, pass: String, cPass: String) {
        if (TextUtils.isEmpty(email)) {
            editTextEmail.requestFocus()
            editTextEmail.setError("Cannot be empty")
        } else if (TextUtils.isEmpty(pass)) {
            editTextPassword.requestFocus()
            editTextPassword.setError("Cannot be empty")
        } else if (TextUtils.isEmpty(cPass)) {
            editTextCPassword.requestFocus()
            editTextCPassword.setError("Cannot be empty")
        } else if (!verifyEmail(email)) {
            editTextEmail.requestFocus()
            editTextEmail.setError("Invalid email")
        }else if (!verifyPassword(pass)){
            Toast.makeText(this,"Check your password length",Toast.LENGTH_LONG).show()
//            editTextPassword.requestFocus()
//            editTextPassword.setError("Invalid password")
        }else if (!passMatch(pass,cPass)){
            editTextCPassword.requestFocus()
            editTextCPassword.setError("Invalid password")
        }else{
            //register
            val user = Details(email,pass)
            dbHandler.insertData(user)
           Toast.makeText(this,"Register success",Toast.LENGTH_LONG).show()
        }
    }


}
