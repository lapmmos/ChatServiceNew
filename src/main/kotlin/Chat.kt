package ru.netology

data class Chat(
    val id: Int,
    val name: String,
    val autorId: Int,
    val companionId: Int){
    var messages = mutableListOf<Message>()

}