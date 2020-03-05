package com.example.chatclient

import android.util.Log
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

class ChatConnector(val activity: MainActivity): Runnable {


    val ip = "192.168.10.47" //koulu 10.0.2.2

    val port = 30001


    private lateinit var printer: PrintStream
    private lateinit var stream: InputStream
    private lateinit var out: OutputStream
    private lateinit var scanner: Scanner

    var input = ""



    override fun run() {

        val s = Socket(ip, port)

        stream = s.getInputStream()
        out = s.getOutputStream()
        printer = PrintStream(out, true)

        scanner = Scanner(stream)


        input = scanner.nextLine()
        var chatMessage = Json.parse(ChatMessage.serializer(), input)
        activity.updateUI(chatMessage.toString())

        while (true) {

                input = scanner.nextLine()
                chatMessage = Json.parse(ChatMessage.serializer(), input)
                activity.updateUI(chatMessage.toString())

        }

    }


    fun sendMessage(chatMessage: ChatMessage){
        println(chatMessage)
        printer.println(Json.stringify(ChatMessage.serializer(), chatMessage))
       // println("sent")
    }




}