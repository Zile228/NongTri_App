package com.example.nngtr

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// Request data model – matching the API request body.
data class MessageRequest(
    val question: String,
    val session_id: String? = null // Cho phép null để API có thể tạo session mới
)


// Response data model – matching the successful API response.
data class MessageResponse(
    val response: String,
    val session_id: String
)

// Optional: Data models for validation error responses.
data class ValidationError(
    val loc: List<Any>,
    val msg: String,
    val type: String
)

data class ErrorResponse(
    val detail: List<ValidationError>
)

// Retrofit interface to interact with your FastAPI endpoint.
interface ChatbotApiService {
    @POST("chat") // Adjust the endpoint path if needed.
    suspend fun sendMessage(@Body request: MessageRequest): Response<MessageResponse>
}
