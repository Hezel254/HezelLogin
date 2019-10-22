package com.example.hezellogin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hezellogin.helper.DbHelper
import com.example.hezellogin.model.Task
import kotlinx.android.synthetic.main.add_task.*
import kotlinx.android.synthetic.main.add_task.view.*
import java.util.*

class TaskActivity : AppCompatActivity() {
    lateinit var rv_task: RecyclerView
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        rv_task = findViewById(R.id.rv_task)
        dbHelper = DbHelper(this, null)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_task, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                //add task
                addtask()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun addtask() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_task, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
        val alertDialog = builder.show()
        dialogView.button_cancel.setOnClickListener {
            alertDialog.dismiss()
            alertDialog.cancel()
        }
        dialogView.button_save.setOnClickListener {

            val date = Date()

            val data =
                Task(dialogView.editTextName.text.toString(), dialogView.editTextDesc.text.toString(), date.toString())
            Log.d("DATA:",data.toString())
            dbHelper.inserttask(data)
            alertDialog.dismiss()
            alertDialog.cancel()
        }
    }


}
