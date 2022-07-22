package ru.netology

fun main() {

    val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет всем")
    val chatById = ChatService.getChatById(newChat.id)
    val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
    val messages = ChatService.getMessages(newChat, 1)
    println(messages)

}