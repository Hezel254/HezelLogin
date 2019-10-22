package com.example.hezellogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hezellogin.adapter.TaskAdapter
import com.example.hezellogin.helper.DbHelper
import com.example.hezellogin.model.Task
import kotlinx.android.synthetic.main.add_task.*
import kotlinx.android.synthetic.main.add_task.view.*
import java.util.*

class TaskActivity : AppCompatActivity() {
    lateinit var rv_task: RecyclerView
    lateinit var dbHelper: DbHelper
    private var taskLists: MutableList<Task>? = null
    private var taskAdapter: TaskAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        rv_task = findViewById(R.id.rv_task)
        dbHelper = DbHelper(this, null)
        rv_task.layoutManager = LinearLayoutManager(this)
        rv_task.setHasFixedSize(true)
        taskLists = ArrayList()
        taskAdapter = TaskAdapter(
            this@TaskActivity,
            taskLists as ArrayList<Task>
        )
        rv_task.setAdapter(taskAdapter)

        loadItems()
        taskAdapter!!.notifyDataSetChanged()
    }

    private fun loadItems() {
        val cursor = dbHelper.getTask()
        (taskLists as ArrayList<Task>).clear()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val desc = cursor.getString(2)
            val date = cursor.getString(3)

            var cart = Task(id, name, desc, date)

            (taskLists as ArrayList<Task>).add(cart)
            taskAdapter?.notifyDataSetChanged()
        }
        taskAdapter?.notifyDataSetChanged()
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
            loadItems()
        }
    }


}
