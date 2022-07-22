package ru.netology

import org.junit.Test

import org.junit.Assert.*
import ru.netology.ChatService.getMaxGuidInList

class ChatServiceTest {

    @Test
    fun addChat() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        assertNotEquals(newChat.id, 0)
    }

    @Test
    fun getChatById() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val chatById = ChatService.getChatById(newChat.id)
        assertNotNull(chatById)
    }

    @Test
    fun getChatById_Null() {
        val chatById = ChatService.getChatById(-1)
        assertNull(chatById)
    }

    @Test
    fun deleteChat() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        ChatService.deleteChat(newChat.id)
        val chatById = ChatService.getChatById(newChat.id)
        assertNull(chatById)
    }

    @Test
    fun deleteChat_Null() {
        val count = ChatService.getChats().size
        ChatService.deleteChat(-1)
        assertEquals(count, ChatService.getChats().size)
    }

    @Test
    fun getChats() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        ChatService.getChats().size
        assertNotEquals(ChatService.getChats().size, 0)
    }

    @Test
    fun addMessage() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        assertEquals(newChat.messages.size, 2)
    }

    @Test
    fun getMessageById() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.getMessageById(newChat, 1)
        assertNotNull(message)
    }

    @Test
    fun getMessageById_Null() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.getMessageById(newChat, -1)
        assertNull(message)
    }

    @Test
    fun deleteMessage() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val message2 = ChatService.addMessage(newChat , 2, "Привет 1 от 2 еще раз")
        ChatService.deleteMessage(newChat, message.id)
        val messageInList = ChatService.getMessageById(newChat, message.id)

        assertNull(messageInList)
    }

    @Test
    fun deleteMessage_Null() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val message2 = ChatService.addMessage(newChat , 2, "Привет 1 от 2 еще раз")
        val count = newChat.messages.size
        ChatService.deleteMessage(newChat, -1)

        assertEquals(newChat.messages.size, count)
    }

    @Test
    fun deleteMessage_All() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val message2 = ChatService.addMessage(newChat , 2, "Привет 1 от 2 еще раз")
        ChatService.deleteMessage(newChat, 1)
        ChatService.deleteMessage(newChat, message2.id)
        ChatService.deleteMessage(newChat, message.id)
        val newChatInList = ChatService.getChatById(newChat.id)

        assertNull(newChatInList)
    }

    @Test
    fun editMessage() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val message2 = ChatService.addMessage(newChat , 2, "Привет 1 от 2 еще раз")
        ChatService.editMessage(newChat, message.copy(text = "Просто привет!"))
        val messageInList = ChatService.getMessageById(newChat, message.id)

        assertEquals(messageInList?.text ?: "", "Просто привет!")
    }

    @Test
    fun editMessage_NotAutor() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val message2 = ChatService.addMessage(newChat , 2, "Привет 1 от 2 еще раз")
        ChatService.editMessage(newChat, message.copy(text = "Просто привет!", autorId = 1))
        val messageInList = ChatService.getMessageById(newChat, message.id)

        assertEquals(messageInList?.text ?: "", "Привет 1 от 2")
    }

    @Test
    fun editMessage_Null() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val message2 = ChatService.addMessage(newChat , 2, "Привет 1 от 2 еще раз")
        ChatService.editMessage(newChat, message.copy(id = -1, text = "Просто привет!", autorId = 1))
        val messageInList = ChatService.getMessageById(newChat, message.id)

        assertEquals(messageInList?.text ?: "", "Привет 1 от 2")
    }

    @Test
    fun getMessages() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val messages = ChatService.getMessages(newChat, 1)

        assertNotEquals(messages.size, 0)
    }

    @Test
    fun getUnreadChats() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")

        val unreadChats = ChatService.getUnreadChats(1)


        assertNotEquals(unreadChats.size, 0)
    }

    @Test
    fun getUnreadChats_NotAlone() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")

        val unreadChats = ChatService.getUnreadChats(1)
        ChatService.getMessages(newChat, 1)
        val unreadChats2 = ChatService.getUnreadChats(1)


        assertNotEquals(unreadChats.size, unreadChats2.size)
    }


    @Test
    fun getUnreadChats_easy() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val unreadChats = ChatService.getUnreadChats(1)

        assertNotEquals(unreadChats.size, 0)
    }

    @Test
    fun getUnreadChats_OnDeleteMessage() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val unreadChats = ChatService.getUnreadChats(1)
        ChatService.deleteMessage(newChat, message.id)
        val unreadChats2 = ChatService.getUnreadChats(1)

        assertNotEquals(unreadChats.size, unreadChats2.size)
    }

    @Test
    fun getUnreadChats_OnDeleteChat() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")
        val unreadChats = ChatService.getUnreadChats(1)
        ChatService.deleteChat(newChat.id)
        val unreadChats2 = ChatService.getUnreadChats(1)

        assertNotEquals(unreadChats.size, unreadChats2.size)
    }

    @Test
    fun getUnreadChatsCount() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет 2 от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет 1 от 2")

        val count = ChatService.getUnreadChatsCount(1)
        ChatService.getMessages(newChat, 1)
        val count2 = ChatService.getUnreadChatsCount(1)


        assertNotEquals(count, count2)
    }

    @Test
    fun getMessagesOnFilters() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет от 2")
        val message2 = ChatService.addMessage(newChat , 2, "Как дела")
        val result = ChatService.getMessagesOnFilters(newChat, 2, message.id, 2)
        val result2 = ChatService.getMessagesOnFilters(newChat, 2, message.id)

        assertNotEquals(result.size, 0)
    }

    @Test
    fun getMessagesOnFilters_Null() {
        val newChat = ChatService.addChat("Мой первый чат", 1, 2, "Привет от 1")
        val message = ChatService.addMessage(newChat , 2, "Привет от 2")
        val message2 = ChatService.addMessage(newChat , 2, "Как дела")
        val result = ChatService.getMessagesOnFilters(newChat, 2, -1, 2)

        assertNotEquals(result.size, 0)
    }

    @Test
    fun getMaxGuidInList() {
        val test = mutableListOf(1, 3, 2, 4)
        val id = test.getMaxGuidInList()

        assertEquals(id, 5)
    }

    @Test
    fun getMaxGuidInList_Other() {
        val test = mutableListOf(1.1, 32.3, 2.3)
        val id = test.getMaxGuidInList()

        assertEquals(id, 1)
    }
}