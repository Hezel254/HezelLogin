package com.example.hezellogin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.hezellogin.helper.DbHelper
import com.example.hezellogin.model.Task

class TaskEditActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var editTextDesc: EditText
    lateinit var buttonUpdate: Button
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_edit)
        editTextName = findViewById(R.id.editTextName)
        editTextDesc = findViewById(R.id.editTextDesc)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        dbHelper = DbHelper(this, null)

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val desc = intent.getStringExtra("desc")

        editTextName.setText(name)
        editTextDesc.setText(desc)

        buttonUpdate.setOnClickListener {
//            val data =
//                Task(id.toInt(), editTextName.text.toString(), editTextDesc.text.toString())
            dbHelper.editTask(id.toString(), editTextName.text.toString(), editTextDesc.text.toString())
        }

    }
}
