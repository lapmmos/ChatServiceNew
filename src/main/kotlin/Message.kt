package ru.netology

data class Message(
    val id: Int,
    val autorId: Int,
    val text: String,
    val date: Int,
    val readed: Boolean = false) {
}