package com.example.hezellogin.holder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.hezellogin.R
import com.example.hezellogin.model.Task

class TaskHolder(itemView: View, private val taskObject: List<Task>) :
    RecyclerView.ViewHolder(itemView) {

    var textViewName: TextView
    var textViewDate: TextView
    var textViewDesc: TextView
    var cardViewTask: CardView

    init {
        textViewName = itemView.findViewById(R.id.textViewName)
        textViewDate = itemView.findViewById(R.id.textViewDate)
        textViewDesc = itemView.findViewById(R.id.textViewDesc)
        cardViewTask = itemView.findViewById(R.id.cardViewTask)
    }

}