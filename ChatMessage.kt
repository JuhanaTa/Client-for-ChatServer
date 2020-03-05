package com.example.chatclient

import kotlinx.serialization.Serializable

@Serializable
class ChatMessage(
    val message: String,
    val username: String,
    val time: String,
    val command: String
) {
    override fun toString():String {
        var edMessage = ""
        if(username.length > 1 && time.length > 1){
            edMessage += "${time} "+"${username}: "+message
            return edMessage
        }
        else return message
    }
}