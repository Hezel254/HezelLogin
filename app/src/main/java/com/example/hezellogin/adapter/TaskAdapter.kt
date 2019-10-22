package com.example.hezellogin.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hezellogin.R
import com.example.hezellogin.TaskActivity
import com.example.hezellogin.TaskEditActivity
import com.example.hezellogin.helper.DbHelper
import com.example.hezellogin.holder.TaskHolder
import com.example.hezellogin.model.Task

class TaskAdapter (protected var context: Context, private val task: ArrayList<Task>) :
    RecyclerView.Adapter<TaskHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        var viewHolder: TaskHolder? = null
        val layoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_task, parent, false)
        viewHolder = TaskHolder(layoutView, task)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return this.task.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val dbHelper = DbHelper(context, null)
        val taskAc=TaskActivity()
        holder.textViewName.setText(task[position].name)
        holder.textViewDate.setText(task[position].date)
        holder.textViewDesc.setText(task[position].description)
        holder.cardViewTask.setOnLongClickListener {
            //delete
            dbHelper.deletetask(task[position].id.toString())
            task.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
            true
        }
        holder.cardViewTask.setOnClickListener{
            val intent = Intent(context, TaskEditActivity::class.java).putExtra("id",task[position].id.toString()).putExtra("name",task[position].name.toString()).putExtra("desc",task[position].description.toString())
            context.startActivity(intent)
        }
    }


}