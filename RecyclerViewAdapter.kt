package com.example.chatclient

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context, private var mydata: MutableList<String>):

    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.myitemlayout, vg, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return mydata.size
    }
    override fun onBindViewHolder(vh: ViewHolder, pos: Int) {
        vh.itemView.findViewById<TextView>(R.id.textView).text = mydata[ pos ]

    }

    fun addItem(item: String){
        mydata.add(item)
        notifyDataSetChanged()
    }
    fun removeItems(){
        mydata.clear()
        notifyDataSetChanged()
    }
}