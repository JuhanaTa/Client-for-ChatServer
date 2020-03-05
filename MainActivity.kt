package com.example.chatclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.json.Json
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val mydata = ArrayList<String>()
    lateinit var chatConnector: ChatConnector
    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(this,mydata)
        RecyclerView.adapter = adapter

        chatConnector = ChatConnector(this)
        Thread(chatConnector).start()


            sendButton.setOnClickListener {

                val input = editText.text.toString()
                println(input)
                if (checkCommand(input)){
                    val chatMessage = ChatMessage("", "", "", input)
                    Thread { chatConnector.sendMessage(chatMessage) }.start()
                }
                else {
                    val chatMessage = ChatMessage(input, "", "", "")
                    Thread { chatConnector.sendMessage(chatMessage) }.start()
                }

                editText.setText("")
            }

    }
    fun updateUI(item: String){
        runOnUiThread {
            adapter.addItem(item)
        }
    }

    private fun checkCommand(input: String): Boolean{

        val firstItem = input.substring(0,1)

        return firstItem == "/"
    }

}
