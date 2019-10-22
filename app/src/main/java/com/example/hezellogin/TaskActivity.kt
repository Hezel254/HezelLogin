package com.example.hezellogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class TaskActivity : AppCompatActivity() {
    lateinit var rv_task:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        rv_task=findViewById(R.id.rv_task)
    }
}
