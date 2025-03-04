package com.example.nngtr

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Make sure you reuse the same ChatMessage data class definition used in your UI.
data class ChatMessage(val text: String, val isUser: Boolean)

class ChatbotViewModel : ViewModel() {
    val messages = mutableStateListOf<ChatMessage>()
    private val apiService = ApiClient.retrofit.create(ChatbotApiService::class.java)

    // Chuyển sessionId từ hằng số sang biến có thể cập nhật
    private var sessionId: String? = null

    fun sendUserMessage(question: String) {
        messages.add(ChatMessage(question, isUser = true))
        messages.add(ChatMessage("Loading...", isUser = false))

        viewModelScope.launch {
            try {
                // Xây dựng request, chỉ thêm session_id nếu đã có session trước đó
                val request = MessageRequest(
                    question = question,
                    session_id = sessionId ?: "" // Nếu chưa có session thì gửi chuỗi rỗng
                )

                val response = apiService.sendMessage(request)

                // Xóa tin nhắn "Loading..."
                if (messages.isNotEmpty() && messages.last().text == "Loading...") {
                    messages.removeAt(messages.size - 1)
                }

                if (response.isSuccessful) {
                    val body = response.body()
                    val replyText = body?.response ?: "No response from server."

                    // Cập nhật sessionId từ phản hồi của API
                    body?.session_id?.let {
                        if (sessionId == null) {
                            sessionId = it // Lưu session ID nếu lần đầu được cấp
                        }
                    }

                    messages.add(ChatMessage(replyText, isUser = false))
                } else {
                    messages.add(ChatMessage("Error: ${response.code()}", isUser = false))
                }
            } catch (e: Exception) {
                if (messages.isNotEmpty() && messages.last().text == "Loading...") {
                    messages.removeAt(messages.size - 1)
                }
                messages.add(ChatMessage("Network error: ${e.localizedMessage}", isUser = false))
            }
        }
    }

    fun resetChat() {
        messages.clear()
        sessionId = null // Reset sessionId khi bắt đầu cuộc trò chuyện mới
    }
}

