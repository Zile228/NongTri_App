package com.example.nngtr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chatbot(modifier: Modifier = Modifier, viewModel: ChatbotViewModel = viewModel()) {
    // Use the messages state from the ViewModel.
    val messages = viewModel.messages
    // Current text in the input field.
    var currentMessage by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFE0F5F8))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            ChatHeader()

            // Conversation area (shows messages)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (messages.isEmpty()) {
                    Conversation()
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(messages) { message ->
                            if (message.isUser) {
                                // User message (right aligned)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = Color(0xFFEAE868),
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .widthIn(max = 250.dp)
                                            .padding(8.dp)
                                    ) {
                                        Text(
                                            text = message.text.trim(),
                                            color = Color.Black
                                        )
                                    }
                                }
                            } else {
                                // Chatbot message (left aligned)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    // Chatbot avatar on the left
                                    Image(
                                        painter = painterResource(id = R.drawable.chatbotavt),
                                        contentDescription = "Chatbot avatar",
                                        modifier = Modifier
                                            .size(36.dp)
                                            .padding(end = 4.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    // White bubble for chatbot message
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = Color.White,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .width(250.dp)
                                            .padding(8.dp)
                                    ) {
                                        Text(
                                            text = message.text.trim(),
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Message input area
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                TextField(
                    value = currentMessage,
                    onValueChange = { currentMessage = it },
                    textStyle = TextStyle(color = Color.Black),
                    placeholder = { Text(text = "Nhập câu hỏi tại đây...", color = Color.Gray) },
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 35.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFEAE868),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = false,
                    maxLines = 4,
                    keyboardOptions = KeyboardOptions.Default
                )

                // Show send icon only when input is not blank
                if (currentMessage.isNotBlank()) {
                    IconButton(
                        onClick = {
                            // Call the ViewModel function to send the message.
                            viewModel.sendUserMessage(currentMessage)
                            // Clear the input field.
                            currentMessage = ""
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sendmessage),
                            contentDescription = "Send Message",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatHeader() {
    Box(
        modifier = Modifier
            .padding(0.dp)
            .width(393.dp)
            .height(100.dp)
    ) {
        Box(
            modifier = Modifier
                .width(393.dp)
                .height(100.dp)
                .background(color = Color(0xFFFF9D23))
        )
        Image(
            painter = painterResource(id = R.drawable.chatboticon),
            contentDescription = "Header icon of Chat screen",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 107.dp, top = 54.dp)
                .width(54.dp)
                .height(46.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "Bạn cần gì? \nNông Trí đây!",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .offset(x = 161.dp, y = 53.dp)
                .width(126.dp)
                .height(44.dp)
        )
    }
}

@Composable
fun Conversation() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Để bắt đầu cuộc trò chuyện, hãy đặt câu hỏi cho Nông Trí. \nVí dụ: Cần lưu ý gì khi cà phê đang ra hoa?",
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.montserratitalic)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .offset(y = 8.dp)
                .align(Alignment.Center)
        )
    }
}